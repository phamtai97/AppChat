# App Chat Smile Alo
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

![Imgur](https://i.imgur.com/e8SGHOA.png)

![Imgur](https://i.imgur.com/N2hg0ta.png)

## Database
- Sử dụng RockDB
- Định nghĩa các key-value trong DB

    [user.userName; Object user]

    [channel.channelName; Object channel]

    [channelName.members; list members]

    [userName.channels; list channels]

    [messageId; Object Message]

    [channelName.messages; list messageId]

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

## Function
- Login:

![Imgur](https://i.imgur.com/G4gQ8PY.png)

![Imgur](https://i.imgur.com/B1FNSzi.png)

- Login failed:

![Imgur](https://i.imgur.com/eQOL3Am.png)

- Register:

![Imgur](https://i.imgur.com/yXAz8lE.png)

![Imgur](https://i.imgur.com/B1FNSzi.png)

- Register failed:

![Imgur](https://i.imgur.com/DyHZkmg.png)

- Create Room:

![Imgur](https://i.imgur.com/eySbfWd.png)

![Imgur](https://i.imgur.com/TXQRRBx.png)

![Imgur](https://i.imgur.com/8m3Tvth.png)

![Imgur](https://i.imgur.com/fgSKmTT.png)

- Create Room failed:

![Imgur](https://i.imgur.com/ZuEPuyE.png)

- Chat Room:

![Imgur](https://i.imgur.com/coBFNFv.png)

![Imgur](https://i.imgur.com/Jp1kUZc.png)

![Imgur](https://i.imgur.com/s1LB9US.png)

- Chat 1 vs 1

![Imgur](https://i.imgur.com/TiIyMfm.png)

![Imgur](https://i.imgur.com/tmKlN86.png)

- Logout

![Imgur](https://i.imgur.com/bf5jti7.png)

- Other funtion: Load list Room chat, load all message in channel, find user.