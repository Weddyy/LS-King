# Project : LoL Stone
# App : Main server in project LoL Stone "King"

Database : MySQL ( use hibernate )

TCP/IP : Netty


Connects:
King <-> Monkey (count : 1....n) need for see,when player start game

King <-> Camera (count : 1....n) need for save game replays

King <-> STServer (count : 1) need for see backup game

King <-> Web (count : 1) web interface



Liberties:
PacketWorker
