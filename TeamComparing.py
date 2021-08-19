
import InfoGathering

import math

teams = {}


"""
:param teamName: A str contains a legal team name
:return: the elo points of corresponding team, if no team added yet, return 1600 as a default value
:post effects: if not corresponding team exist in dictionary teams, will create a new one in the dictionary

"""

def getTeamElo(teamName: str):
    try:
        return teams[teamName]
    except:
        teams[teamName] = 1600
        return 1600

"""
:param teamName: A str contains a legal team name
:return: the elo points of corresponding team, if no team added yet, return 1600 as a default value
:post effects: if not corresponding team exist in dictionary teams, will create a new one in the dictionary

"""

def setTeamElo(teamName: str, newElo:float):

    teams[teamName] = newElo

"""
:param opponentElo: The elo point of the opponent
:param gameResult:  the result of the game, 1 for win, 0 for draw and -1 for lose
:param factor:  if the team playing at home, temporary elo point + 100, if playing away, temporary points - 100
:param k: the k factor calculated by the gap between two team's elo points
:return: the new self elo points
:post: no post effect
"""


def updateElo(selfElo: int, opponentElo: int, gameResult: float, factor: int, k : int):
    temporary_elo_mark = selfElo + factor

    expect_result = 1 / (1 + math.pow(10, (opponentElo - temporary_elo_mark) / 400))

    new_selfElo = selfElo + k * (gameResult - expect_result)

    return new_selfElo


'''
sample dict format: {'HomeTeam': 'SD Eibar',
                    'AwayTeam': 'Celta de Vigo', 
                    'Score': '0:0', 
                    'HomeGoal': 0, 
                    'AwayGoal': 0}
'''


"""
:param gameDict: A dictionary in same format as above sample
:return: nothing
:post effects: the elo points of the 2 teams in dictionary 'teams' is updated

"""
def calculateAGame(gameDict: dict):

# get the datas
    home = gameDict['HomeTeam']
    away = gameDict['AwayTeam']
    homeGoal = gameDict['HomeGoal']
    awayGoal = gameDict['AwayGoal']


    homeELo = getTeamElo(home)
    awayElo = getTeamElo(away)

# calculated factor k base on the game result and differences of elo points
    gameResult = 0.5
    eloDifference = (homeELo+awayElo)/2
    if homeGoal > awayGoal:
        gameResult = 1
        eloDifference = homeELo - awayElo
    elif homeGoal < awayGoal:
        gameResult = 0
        eloDifference = awayElo - homeELo

    if eloDifference > 400:
        k = 20
    elif eloDifference > 200:
        k = 24
    elif eloDifference < -400:
        k = 36
    elif eloDifference < - 200:
        k = 32
    else:
        k = 28

# calculate new elo points and update
    newHomeElo = updateElo(homeELo, awayElo, gameResult, 100, k)
    newAwayElo = updateElo(awayElo, homeELo, math.fabs(gameResult-1), -100, k)

    setTeamElo(away,newAwayElo)
    setTeamElo(home,newHomeElo)



if __name__ == '__main__':
    leagues = InfoGathering.getLeagueNames()
    for i in range(len(leagues)):
        print(i+1, " : ", leagues[i])
    league_choice = input("Please Enter The Number of the League: ")
    league_year_start = input("Please Enter The Starting Season Of the League From 2000 to 2019(Use The Early Year, "
                              "For Example 2019 for 2019-2020)")
    league_year_end = input("Please Enter The Last Season Of the League from 2001 to 2020(Use The Early Year, "
                            "For Example 2019 for 2019-2020)")

    league_year_start = int(league_year_start)
    league_year_end = int(league_year_end)
    league_choice = int(league_choice)

    while league_choice > len(leagues) or league_choice <= 0:
        league_choice = input("Please Re-Enter A Valid Number")
        league_choice = int(league_choice)

    while league_year_start < 2000 or league_year_start > 2019:
        league_year_start = input("Please Re-Enter A Valid Start Season")
        league_year_start = int(league_year_start)

    while league_year_end< 2001 or league_year_start > 2020:
        league_year_end = input("Please Re-Enter A Valid Last Season")
        league_year_end = int(league_year_end)

    print('You have chose ', leagues[league_choice-1], " from ", league_year_start, " to ", league_year_end)

    print("Please Wait...")
    for year in range(league_year_start, league_year_end+1):
        print("Please Wait... ")
        for i in InfoGathering.getYearResult(leagues[league_choice-1], year):
            calculateAGame(i)

    for k in sorted(teams, key=teams.get):
        print('Team: ' + k + '\t\t\tRank Point: ' + str(teams[k]))