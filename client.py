import socket
sock = socket.socket()
server = '127.0.0.1'
port = 30
sock.connect((server,port))
print sock.recv(1024)
sock.close()
