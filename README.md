# Carrefour-Simulator
simulation d'un carrefour routière par système multi-agent (JADE) 


# Generate Candidate
This module contain Candidate Generation Module for Entity Linking Project ### HenceExtract
## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install foobar
```

## Usage
in this Example we will get 4 candidates for given mention"jordan" in english language

```python
import CandidateGeneration as CG
import pandas as pd
data_candidates=CG.get_wiki_candidates("Jordan","en",4)
df=pd.DataFrame.from_dict(data_candidates, orient='index')
df
```
the result is :

| Candidate | Wikipedia_link| Content(wikidata/wikipedia) |Wikidata_ID |
| :---         |     :---:      |          :---: |         ---: |
| Jordan   | https://en.wikipedia.org/wiki/Jordan     | Country in the Middle East    |Q810    |
| Michael Jordan     | https://en.wikipedia.org/wiki/Michael_Jordan       | American basketball player and businessman      |Q41421     |
| Air Jordan     | https://en.wikipedia.org/wiki/Air_Jordan       | conteeeeeeeeeeeeent     |Q420953     |
| Jordan Belfort     | https://en.wikipedia.org/wiki/Jordan_Belfort       | American fraudster, author, motivational speak...      |Q3183674      |



Candidate	Wikipedia_link	Content(wikidata/wikipedia)	Wikidata_ID
0	Jordan	https://en.wikipedia.org/wiki/Jordan	Country in the Middle East	Q810
1	Michael Jordan	https://en.wikipedia.org/wiki/Michael_Jordan	American basketball player and businessman	Q41421
2	Air Jordan	https://en.wikipedia.org/wiki/Air_Jordan	conteeeeeeeeeeeeent	Q420953
4	Jordan Belfort	https://en.wikipedia.org/wiki/Jordan_Belfort	American fraudster, author, motivational speak...	Q3183674

## Description

### The Main function : 
#### get_wiki_candidates(query,language,cand_number)
    get all candidates for a given mention

### The Sub-functions :
#### search_wiki(query_candidate,language)
    get all informations for a given query (candidate) from Wikipedia and Wikidata 
#### disambiguisation(exception_response)
    get new candidates from error message of disambiguisation error "wikipedia.exceptions.DisambiguationError"
#### get_wikidata_infos(suffix,wikip_id,langue)
    Get wikidata Id and wikidata description for a given wikipedia ID
