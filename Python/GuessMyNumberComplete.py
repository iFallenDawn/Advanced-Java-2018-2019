# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""
import random
magicNumber = random.randint(1, 101)
'''Create a variable for the guess, and ask the user to guess a number from 1 to 100'''
guessNumber = int(input("Enter A Number from 1 to 100: "))
'''Create a while loop to run if the guessed number is not equal to the magic number'''
'''Compare The Numbers and Return if the Guess is too high or too low'''
'''If they are not equal then ask for the user to guess again'''
'''Make sure to keep track of how many guess they take!'''
count = 0
while guessNumber != magicNumber:
 count = count + 1
 if guessNumber < magicNumber:
   print("Too Low!")
 else:
   print("Too High!")
 guessNumber = int(input("Guess another number from 1 to 100: "))
count = count + 1
'''Tell them if they got it right and tell them the number!'''
'''Also tell them How many tries it took them!'''
print("You got it right! Your number Was: " + str(magicNumber))
print("It took you " + str(count) + " Tries!")
