# App Chat Smile Alo
## Introduce
- App Smile Alo là ứng dụng chat trên môi trường desktop, được viết bằng ngôn ngữ JAVA.
- Người dùng có thể tạo tài khoản chat, có thể chat one-one hoặc tạo group chat one-many.
- Dữ liệu app người dùng cũng như các tin nhắn, thông tin group chat sẽ được lưu bằng RockDB.

## Client
- Sử dụng framework Netty Java.
- Graphical User Interface: framework Swing Java.
- Sử dụng Protocol Buffer để serialize dữ liệu thành dạng byte stream để gửi nhận giữa server và client.

## Server 
- Sử dụng framework Netty Java.
- password: dùng base64Encode (k hợp lí, nên hash).
- jwt token: dùng hmacSha256.
- Sử dụng Protocol Buffer để serialize dữ liệu thành dạng byte stream để gửi nhận giữa server và client.
- Định nghĩa file proto:
- Server có ghi log lại các quá trình gửi nhận của client tới server như login, register, gửi message chat, tạo room chat,...

## Database
- Sử dụng RockDB
- Định nghĩa các key-value trong DB

    `[user.userName; Object user]`

    `[channel.channelName; Object channel]`

    `[channelName.members; list members]`

    `[userName.channels; list channels]`

    `[messageId; Object Message]`

    `[channelName.messages; list messageId]`

    Object user: 
    
        String _name;
        String _userName;
        String _password;
        
    Object Channel:

        String _userCreate;
        String _channelName;
        byte[] _avatar;
        String _listMembers;
    
    Objecct Message:

        String _id;
        String _body;
        String _userSend;
        String _timeSend;

## Image Demo

![](https://media.giphy.com/media/iNKB2snFBDCMW5MZwo/giphy.gif)
