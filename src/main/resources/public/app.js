window.onload = function (e) {
    liff.init(function (data) {
        initializeApp(data);
    });
    // initializeApp();
};

function initializeApp(data) {
    let url = '/api/list/' + data.context.userId;
    // let url = '/api/list/' + 'U2d46bcd5a990b1ba8528829eae526238';
    fetch(url)
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                // Examine the text in the response
                response.json().then(function(resp) {
                    console.log(resp);
                    renderTasks(resp);
                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });


    // closeWindow call
    // document.getElementById('closewindowbutton').addEventListener('click', function () {
    //     liff.closeWindow();
    // });

    // // sendMessages call
    // document.getElementById('sendmessagebutton').addEventListener('click', function () {
    //     liff.sendMessages([{
    //         type: 'text',
    //         text: "You've successfully sent a message! Hooray!"
    //     }, {
    //         type: 'sticker',
    //         packageId: '2',
    //         stickerId: '144'
    //     }]).then(function () {
    //         window.alert("Message sent");
    //     }).catch(function (error) {
    //         window.alert("Error sending message: " + error);
    //     });
    // });

    //get profile call
    // document.getElementById('getprofilebutton').addEventListener('click', function () {
    //     liff.getProfile().then(function (profile) {
    //         document.getElementById('useridprofilefield').textContent = profile.userId;
    //         document.getElementById('displaynamefield').textContent = profile.displayName;
    //
    //         var profilePictureDiv = document.getElementById('profilepicturediv');
    //         if (profilePictureDiv.firstElementChild) {
    //             profilePictureDiv.removeChild(profilePictureDiv.firstElementChild);
    //         }
    //         var img = document.createElement('img');
    //         img.src = profile.pictureUrl;
    //         img.alt = "Profile Picture";
    //         profilePictureDiv.appendChild(img);
    //
    //         document.getElementById('statusmessagefield').textContent = profile.statusMessage;
    //         toggleProfileData();
    //     }).catch(function (error) {
    //         window.alert("Error getting profile: " + error);
    //     });
    // });
}


function renderTasks(list) {
    let container = document.getElementById('container');
    list.forEach(data => {
        let el = document.createElement('div');
        el.className = 'task';
        el.innerText = data;
        container.appendChild(el)
    })
}

// function toggleProfileData() {
//     var elem = document.getElementById('profileinfo');
//     if (elem.offsetWidth > 0 && elem.offsetHeight > 0) {
//         elem.style.display = "none";
//     } else {
//         elem.style.display = "block";
//     }
// }