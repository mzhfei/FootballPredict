from bs4 import BeautifulSoup as soup
import urllib3 as urllib

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36'}

''' The website'''
urlMainTitle = "https://www.transfermarkt.com/"

'''
    Dictionary contains all league websites urls
    String 'league name' is the key
    String 'url' is the value

    FUTURE UPDATE : keep these value into a txt/xml file and read from file
'''
league_websites = {
    "SERIE_A": "serie-a/gesamtspielplan/wettbewerb/IT1/saison_id/",
    "BUNDESLIGA": "bundesliga/gesamtspielplan/wettbewerb/L1/saison_id/",
    "PREMIER_LEAGUE": "premier-league/gesamtspielplan/wettbewerb/GB1/saison_id/",
    "LALIGA": "laliga/gesamtspielplan/wettbewerb/ES1/saison_id/",
    "LIGUE_1": "ligue-1/gesamtspielplan/wettbewerb/FR1/saison_id/",
}

'''
Set a pool manager
use headers to pretend as a browser

'''
http = urllib.PoolManager()
http.headers = headers
test_url_laliga = "laliga/gesamtspielplan/wettbewerb/ES1/saison_id/2018"

"""
"""



def getYearResult(league, year):
    def get_result(match_bs4):
        team1 = match_bs4.find('td', attrs='text-right no-border-rechts hauptlink').find('a').text
        result = match_bs4.find('td', attrs='zentriert hauptlink').find('a').text
        team2 = match_bs4.find('td', attrs='no-border-links hauptlink').find('a').text
        home_goal = ''
        away_goal = ''

        temp = ''
        for k in range(0, len(result)):
            if result[k] != ':':
                temp += result[k]
                if k == len(result) - 1:
                    away_goal = temp
            else:
                home_goal = temp
                temp = ''
        home_goal = int(home_goal)
        away_goal = int(away_goal)
        match_result_dict = dict()
        match_result_dict['HomeTeam'] = team1
        match_result_dict['AwayTeam'] = team2
        match_result_dict['Score'] = result
        match_result_dict['HomeGoal'] = home_goal
        match_result_dict['AwayGoal'] = away_goal
        return match_result_dict

    def getRoundResult(gameday_bs4):
        matches = gameday_bs4.findAll('tr', attrs={'class': None})
        round_results = []
        for i in matches:
            round_results.append(get_result(i))
        return round_results

    if league not in league_websites:
        print("League name not supported")
        return []
    elif year > 2021 or year < 2000:
        print("Year not supported, enter year between 2000 to 2021")
        return []

    page = http.request('GET', urlMainTitle + league_websites[league] + str(year))
    page_data = soup(page.data, 'html.parser')
    all_round_table = page_data.findAll('div', attrs={'class': "large-6 columns", 'id': None})
    qOfGames = []
    for i in all_round_table:
        tr = i.find('tbody')
        qOfGames.extend(getRoundResult(tr))
    return qOfGames


def getLeagueNames():
    return league_websites.keys()