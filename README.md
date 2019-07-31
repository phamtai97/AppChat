# App Chat Smile Alo

<div align="center">
	<img src="https://media.giphy.com/media/iNKB2snFBDCMW5MZwo/giphy.gif">
	<br/>
</div>
<br/>

## Overview
- App Smile Alo là ứng dụng chat trên môi trường desktop, được viết bằng ngôn ngữ JAVA.
- Người dùng có thể tạo tài khoản chat, có thể chat one-one hoặc tạo group chat one-many.
- Dữ liệu app người dùng cũng như các tin nhắn, thông tin group chat sẽ được lưu bằng RockDB.

## Client
- Sử dụng framework Netty Java.
- Graphical User Interface: framework Swing Java.
- Sử dụng Protocol Buffer để serialize dữ liệu thành dạng byte stream để gửi nhận giữa server và client.

## Server 
- Sử dụng framework Netty Java.
- Password: dùng base64Encode (nên dùng các thuật toán hash như SHA, MD5).
- JWT token: dùng `hmacSha256`.
- Sử dụng Protocol Buffer để serialize dữ liệu thành dạng byte stream để gửi nhận giữa server và client.
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
```sh
String _name;
String _userName;
String _password;
```

Object Channel:
```sh
String _userCreate;
String _channelName;
byte[] _avatar;
String _listMembers;
```

Objecct Message:
```sh
String _id;
String _body;
String _userSend;
String _timeSend;
```


