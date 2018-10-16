# Todo Line Bot

This is a java spring boot application using [LINE Messaging API](https://developers.line.me/en/docs/messaging-api/overview/) and MongoDB to demonstrate simple todo BOT.

# Prerequisites
* A [channel on LINE Developers](https://developers.line.me/en/docs/liff/getting-started/) for your application.
* A [LINE channel access token](https://developers.line.me/en/docs/liff/getting-started/#preparing-channel-access-token)

# Running the application

1. export these environment variable
    - `channel_secret` from LINE channel_secret
    - `channel_access_key` from LINE channel_access_token
2. Start MongoDB
3. Build docker from Dockerfile
    ```sh
    docker build -t todo-bot .
    ```
4. Run docker 
    ```sh
    docker run -p 8080:8080 todo-bot
    ```
5. Register webhook URL in LINE bot channel using `https://{yoursecureurl}/api/webhook` (if you running on local you can use [ngrok](https://ngrok.com/) to create a secure URL and forward all the request to golang application)
6. Finish!

# How to use todo bot

1. Add bot as a friend
2. Send message to bot with these format.
    ```
    - task : date/month/year : time e.g Buy milk : 3/5/18 : 13:00
    - task : today : time e.g Finsh writing shopping list : today : 15:30
    - task : tomorrow : time e.g Watch movie : tomorrow : 18:00
    ``` 
3. Send message `edit` to bot, it will reply with todo edit webpage url. You can mark as `completed` or mark as `important` using checkbox
4. Bot will summary daily tasks for you at 12PM and 6PM
