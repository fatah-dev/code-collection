import random

def welcome():
    print('''welcome to this funny game!
I will guess a number between 1 to 20
and you have guess it...
GO GO GO !
             ''')

def finish(number,count):
    print( f'my number was {number} and you found in {count} guesses')
    answer = input('DO you want to play again?(Y/N)')
    if answer.upper()=='Y':
        return True
    else:
        return False


def win(computer_number,guess):
    
    return computer_number == guess
    
def answer(computer,guess):
    if computer > guess:
         return'My number is larger'
    if computer < guess:
         return'My number is smaller'
    return'\nOhh...!You won ☻\n'
    
    
    
def get_a_guess():
    return int(input('Whats your guess?'))

'''........................................'''


continue_playing=True

welcome()

while(continue_playing):

    computer_number=random.randint(1,20)
    guess=0
    count=0


    
    while (not win(computer_number, guess)):
        guess = get_a_guess()
        count+=1
        print(answer(computer_number, guess))
    continue_playing=finish(computer_number,count)
