window.onload = function (e) {
    liff.init(function (data) {
        initializeApp(data);
    });
};

function initializeApp(data) {
    list(data.context.userId);
}


function renderTasks(list) {
    let container = document.getElementById('container');
    container.innerHTML = null;

    list.forEach((todo, i) => {
        let taskContainer = document.createElement('div');
        let taskContainerClassList = taskContainer.classList;
        taskContainerClassList.add('task');
        if (todo.completed === true) {
            taskContainerClassList.add('strike');
        }

        let completeContainer = document.createElement('div');
        let completeContainerClassList = completeContainer.classList;
        completeContainerClassList.add('checkbox-container', 'complete-mark');

        let completeWrapper = document.createElement('label');
        completeWrapper.className = 'checkbox-wrapper';

        let complete = document.createElement('input');
        complete.setAttribute('type', 'checkbox');
        complete.setAttribute('value', i);
        if (todo.completed === true) {
            complete.setAttribute('checked', 'true');
        }
        complete.addEventListener('change', function(e) {
            let taskId = todo.id;
            let userId = todo.user;
            let flag = e.target.checked;
            markComplete(userId, taskId, flag);
        });

        let completeMark = document.createElement('span');
        completeMark.className = 'checkbox-mark';
        completeWrapper.appendChild(complete);
        completeWrapper.appendChild(completeMark);
        completeContainer.appendChild(completeWrapper);


        let task = document.createElement('p');
        let taskClassList = task.classList;
        taskClassList.add('task');
        task.innerText = todo.task;


        let time = document.createElement('p');
        let text = todo.time;
        time.className = 'task';
        text = text.replace('T', ' ');
        time.innerText = text;


        let pinContainer = document.createElement('div');
        let pinContainerClassList = pinContainer.classList;
        pinContainerClassList.add('checkbox-container', 'pin-mark');

        let pinWrapper = document.createElement('label');
        pinWrapper.className = 'checkbox-wrapper';

        let pin = document.createElement('input');
        pin.setAttribute('type', 'checkbox');
        pin.setAttribute('value', i);
        if (todo.important === true) {
            pin.setAttribute('checked', 'true');
        }
        pin.addEventListener('change', function(e) {
            let taskId = todo.id;
            let userId = todo.user;
            let flag = e.target.checked;
            markImportant(userId, taskId, flag);
        });

        let pinMark = document.createElement('span');
        pinMark.className = 'checkbox-mark';
        pinWrapper.appendChild(pin);
        pinWrapper.appendChild(pinMark);
        pinContainer.appendChild(pinWrapper);


        taskContainer.appendChild(completeContainer);
        taskContainer.appendChild(task);
        taskContainer.appendChild(time);
        taskContainer.appendChild(pinContainer);
        container.appendChild(taskContainer);
    })
}

function list(userId) {
    let url = '/api/list/' + userId;
    fetch(url)
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                response.json().then(function(resp) {
                    console.log(resp);
                    renderTasks(resp);
                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
}

function markComplete(userId, taskId, flag) {
    let url = '/api/completed/' + userId;
    let payload = {
        id: taskId,
        flag: flag
    };

    fetch(url, {
            method: 'PUT',
            body: JSON.stringify(payload),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .then(function(response){
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' + response.status);
                return;
            }

            response.json().then(function(resp) {
                renderTasks(resp);
            });
        })
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
}

function markImportant(userId, taskId, flag) {
    let url = '/api/important/' + userId;
    let payload = {
        id: taskId,
        flag: flag
    };

    fetch(url, {
        method: 'PUT',
        body: JSON.stringify(payload),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
        .then(function(response){
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' + response.status);
                return;
            }

            response.json().then(function(resp) {
                renderTasks(resp);
            });
        })
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
}
