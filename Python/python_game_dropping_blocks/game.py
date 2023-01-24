import pygame
import sys
import time
import random
import os
import neat

pygame.init()

WIDTH = 800
HEIGHT = 600

RED = (255, 0, 0)
BLACK = (0, 0, 0)
BACKGROUND_COLOR = (0, 0, 0)
BLUE = (0, 0, 255)
WHITE = (255, 255, 255)

player_size = 20
player_speed = 5
player_pos = [WIDTH / 2, HEIGHT - 10 - player_size]

x = player_pos[0]
y = player_pos[1]

screen = pygame.display.set_mode((WIDTH, HEIGHT))

game_over = False

pygame.key.set_repeat(1)
clock = pygame.time.Clock()

myFont = pygame.font.SysFont("couriernew", 35)
print(pygame.font.get_fonts())

FADE_STRENGTH = 10

last_spot_pos = []
for i in range(0, FADE_STRENGTH):
    last_spot_pos.append(player_pos)

ENEMY_SPEED = 1
ENEMY_SIZE = 20

ENEMY_NUMBER = 1
ENEMY_SPOTS = []
MAX_ENEMIES = 20
ENEMY_ACCELERATOR = 1

score = 0


def spawn_enemy(e_number, e_accelerator):
    if len(ENEMY_SPOTS) <= 20:
        ENEMY_SPOTS.append([random.randint(0, WIDTH - ENEMY_SIZE), ENEMY_SIZE * (-1)])
        print("ENEMY SPAWNED")
        e_accelerator += 1
        if e_accelerator > 90:
            ENEMY_ACCELERATOR = 90


def move_enemys(s):
    for enemy in ENEMY_SPOTS:
        enemy[1] += ENEMY_SPEED
        if enemy[1] > HEIGHT:
            s += 1
            enemy[0] = random.randint(0, WIDTH - ENEMY_SIZE)
            enemy[1] = ENEMY_SIZE * (-1)
        pygame.draw.rect(screen, BLUE, (enemy[0], enemy[1], ENEMY_SIZE, ENEMY_SIZE))
    return s


def make_fade():
    for i in range(0, len(last_spot_pos) - 1):
        last_spot_pos[i] = last_spot_pos[i + 1]
        pygame.draw.rect(screen, (255 / len(last_spot_pos) * (i + 1), 0, 0),
                         (last_spot_pos[i][0], last_spot_pos[i][1], player_size, player_size))
    last_spot_pos[len(last_spot_pos) - 1] = player_pos
    pygame.draw.rect(screen, (255 / len(last_spot_pos) * (len(last_spot_pos)), 0, 0),
                     (last_spot_pos[i][0], last_spot_pos[i][1], player_size, player_size))


def check_collision(e_spots):
    for enemy in e_spots:
        if (player_pos[0] <= enemy[0] < (player_pos[0] + player_size)) or (
                enemy[0] <= player_pos[0] < (enemy[0] + ENEMY_SIZE)):
            if (player_pos[1] <= enemy[1] < (player_pos[1] + player_size)) or (
                    enemy[1] <= player_pos[1] < (enemy[1] + ENEMY_SIZE)):
                return True
    return False


while not game_over:

    _wait = True

    for event in pygame.event.get():
        screen.fill(BACKGROUND_COLOR)

        # Exit:
        if event.type == pygame.QUIT:
            sys.exit()

        # Movement:
        if event.type == pygame.KEYDOWN:
            x = player_pos[0]
            y = player_pos[1]

            if event.key == pygame.K_a:
                x -= player_speed
            if event.key == pygame.K_d:
                x += player_speed
            if event.key == pygame.K_w:
                y -= player_speed
            if event.key == pygame.K_s:
                y += player_speed
            if x < 0:
                x = 0
            if x > WIDTH - player_size:
                x = WIDTH - player_size
            if y < 0:
                y = 0
            if y > HEIGHT - player_size:
                y = HEIGHT - player_size
            player_pos = [x, y]

            # Fade:
            make_fade()

    screen.fill(BACKGROUND_COLOR)

    if ENEMY_NUMBER >= 100 - ENEMY_ACCELERATOR:
        spawn_enemy(ENEMY_NUMBER, ENEMY_ACCELERATOR)
        ENEMY_NUMBER = 0
        if ENEMY_SPEED < ENEMY_SIZE:
            ENEMY_SPEED += 1
    score = move_enemys(score)

    for i in range(0, len(last_spot_pos) - 1):
        last_spot_pos[i] = last_spot_pos[i + 1]
        pygame.draw.rect(screen, (255 / len(last_spot_pos) * (i + 1), 0, 0),
                         (last_spot_pos[i][0], last_spot_pos[i][1], player_size, player_size))
    last_spot_pos[len(last_spot_pos) - 1] = player_pos
    pygame.draw.rect(screen, (255 / len(last_spot_pos) * (len(last_spot_pos)), 0, 0),
                     (last_spot_pos[i][0], last_spot_pos[i][1], player_size, player_size))

    text = "Score: " + str(score)
    label = myFont.render(text, 5, WHITE)
    screen.blit(label, (WIDTH - 200, HEIGHT - 40))

    pygame.draw.rect(screen, RED, (player_pos[0], player_pos[1], player_size, player_size))

    game_over = check_collision(ENEMY_SPOTS)

    pygame.display.update()

    clock.tick(60)
    ENEMY_NUMBER += 1

print(f"Score: {score}")
print("GAME OVER")
