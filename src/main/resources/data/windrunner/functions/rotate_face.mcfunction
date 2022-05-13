execute if entity @s[y_rotation=135..-135,x_rotation=-45..45,nbt={GravityDirection:0}] run scoreboard players set @s rotation 2
execute if entity @s[y_rotation=-135..-45,x_rotation=-45..45,nbt={GravityDirection:0}] run scoreboard players set @s rotation 5
execute if entity @s[y_rotation=-45..45,x_rotation=-45..45,nbt={GravityDirection:0}] run scoreboard players set @s rotation 3
execute if entity @s[y_rotation=45..135,x_rotation=-45..45,nbt={GravityDirection:0}] run scoreboard players set @s rotation 4
execute if entity @s[x_rotation=-90..-45,nbt={GravityDirection:0}] run scoreboard players set @s rotation 1

execute if entity @s[y_rotation=135..-135,x_rotation=-45..45,nbt={GravityDirection:1}] run scoreboard players set @s rotation 2
execute if entity @s[y_rotation=-135..-45,x_rotation=-45..45,nbt={GravityDirection:1}] run scoreboard players set @s rotation 4
execute if entity @s[y_rotation=-45..45,x_rotation=-45..45,nbt={GravityDirection:1}] run scoreboard players set @s rotation 3
execute if entity @s[y_rotation=45..135,x_rotation=-45..45,nbt={GravityDirection:1}] run scoreboard players set @s rotation 5
execute if entity @s[x_rotation=-90..-45,nbt={GravityDirection:1}] run scoreboard players set @s rotation 0

execute if entity @s[y_rotation=135..-135,x_rotation=-45..45,nbt={GravityDirection:2}] run scoreboard players set @s rotation 1
execute if entity @s[y_rotation=-135..-45,x_rotation=-45..45,nbt={GravityDirection:2}] run scoreboard players set @s rotation 5
execute if entity @s[y_rotation=-45..45,x_rotation=-45..45,nbt={GravityDirection:2}] run scoreboard players set @s rotation 0
execute if entity @s[y_rotation=45..135,x_rotation=-45..45,nbt={GravityDirection:2}] run scoreboard players set @s rotation 4
execute if entity @s[x_rotation=-90..-45,nbt={GravityDirection:2}] run scoreboard players set @s rotation 3

execute if entity @s[y_rotation=135..-135,x_rotation=-45..45,nbt={GravityDirection:3}] run scoreboard players set @s rotation 1
execute if entity @s[y_rotation=-135..-45,x_rotation=-45..45,nbt={GravityDirection:3}] run scoreboard players set @s rotation 4
execute if entity @s[y_rotation=-45..45,x_rotation=-45..45,nbt={GravityDirection:3}] run scoreboard players set @s rotation 0
execute if entity @s[y_rotation=45..135,x_rotation=-45..45,nbt={GravityDirection:3}] run scoreboard players set @s rotation 5
execute if entity @s[x_rotation=-90..-45,nbt={GravityDirection:3}] run scoreboard players set @s rotation 2

execute if entity @s[y_rotation=135..-135,x_rotation=-45..45,nbt={GravityDirection:4}] run scoreboard players set @s rotation 1
execute if entity @s[y_rotation=-135..-45,x_rotation=-45..45,nbt={GravityDirection:4}] run scoreboard players set @s rotation 2
execute if entity @s[y_rotation=-45..45,x_rotation=-45..45,nbt={GravityDirection:4}] run scoreboard players set @s rotation 0
execute if entity @s[y_rotation=45..135,x_rotation=-45..45,nbt={GravityDirection:4}] run scoreboard players set @s rotation 3
execute if entity @s[x_rotation=-90..-45,nbt={GravityDirection:4}] run scoreboard players set @s rotation 5

execute if entity @s[y_rotation=135..-135,x_rotation=-45..45,nbt={GravityDirection:5}] run scoreboard players set @s rotation 1
execute if entity @s[y_rotation=-135..-45,x_rotation=-45..45,nbt={GravityDirection:5}] run scoreboard players set @s rotation 3
execute if entity @s[y_rotation=-45..45,x_rotation=-45..45,nbt={GravityDirection:5}] run scoreboard players set @s rotation 0
execute if entity @s[y_rotation=45..135,x_rotation=-45..45,nbt={GravityDirection:5}] run scoreboard players set @s rotation 2
execute if entity @s[x_rotation=-90..-45,nbt={GravityDirection:5}] run scoreboard players set @s rotation 4

function windrunner:rotate_face_a