import socket
sock = socket.socket()
Server = 'localhost'
Port = 30
sock.bind((Server,Port))
sock.listen(5)
while True:
    c , address = sock.accept()
    print "Connected from ",address
    c.send("You are connected\n")
    c.close()
