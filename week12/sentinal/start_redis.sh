
docker rm -f redis-instance-1
docker rm -f redis-instance-2
docker run -d --restart always --name redis-instance-1 -p 8101:6379 redis:5 redis-server
docker run -d -v $(pwd)/slave.conf:/etc/redis/redis.conf --restart always --name redis-instance-2 -p 8102:6379 redis:5 redis-server /etc/redis/redis.conf

docker run -d -v $(pwd)/sentinal1.conf:/etc/redis/sentinal1.conf --restart always --name redis-instance-2 -p 8102:6379 redis:5 redis-server /etc/redis/sentinal1.conf
#
#sleep 2
#docker exec -it redis-instance-1 redis-cli set a b
#docker exec -it redis-instance-2 redis-cli set a b
#docker exec -it redis-instance-2 redis-cli get a
#redis-instance-1
#redis-instance-2
#971ada21392d9f3ec0b042c2acce130be87a47d6d2be0bf7aaf353c63836a7fa
#83a852e82e9c0a11898005e42bad925b8f5215c717a73c3eca7867a4649928ca
#OK
#(error) READONLY You can't write against a read only replica.
#"b"
