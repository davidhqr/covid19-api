## COVID19-API

COVID19-API provides up-to-date data on the covid-19 global pandemic. The API retrieves data from Johns Hopkins University: https://github.com/CSSEGISandData/COVID-19. 
The API provides data on confirmed, deaths, recovered, and active cases by province/state and by country. 
Timeline of confirmed, deaths, and recovered cases for each country is also available.

### API Endpoints

#### Data By Countries and Provinces/States

##### Display global latest data
`GET /api/data/global`

Sample response:
```json
{
   "confirmed":3506729,
   "deaths":247470,
   "recovered":1125236,
   "active":2163668
}
```

<p>&nbsp;</p>


##### Display latest data for all countries
`GET /api/data/countries`

Sample response:
```json
[
   {
      "id":1,
      "countryName":"Afghanistan",
      "iso2":"AF",
      "iso3":"AFG",
      "latitude":"33.93911",
      "longitude":"67.709953",
      "population":38928341,
      "confirmed":2704,
      "deaths":85,
      "recovered":345,
      "active":2274
   },
   {
      "id":2,
      "countryName":"Albania",
      "iso2":"AL",
      "iso3":"ALB",
      "latitude":"41.1533",
      "longitude":"20.1683",
      "population":2877800,
      "confirmed":795,
      "deaths":31,
      "recovered":531,
      "active":233
    },
    "..."
]
```

<p>&nbsp;</p>


##### Display latest data for specified country
`GET /api/data/countries/{country}`

Sample response:
```json
{
   "id":185,
   "countryName":"Canada",
   "iso2":"CA",
   "iso3":"CAN",
   "latitude":"60",
   "longitude":"-95",
   "population":37855702,
   "confirmed":60504,
   "deaths":3795,
   "recovered":24921,
   "active":31788
}
```

<p>&nbsp;</p>


##### Display latest data for specified country with provinces/states if available
`GET /api/data/countries/{country}/details`

Sample response:
```json
{
   "id":185,
   "countryName":"Canada",
   "iso2":"CA",
   "iso3":"CAN",
   "latitude":"60",
   "longitude":"-95",
   "population":37855702,
   "provincesStates":[
      {
         "provinceState":"Alberta",
         "latitude":"53.9333",
         "longitude":"-116.5765",
         "confirmed":5766,
         "deaths":95,
         "recovered":0,
         "active":5671
      },
      {
         "provinceState":"British Columbia",
         "latitude":"53.7267",
         "longitude":"-127.6476",
         "confirmed":2171,
         "deaths":114,
         "recovered":0,
         "active":2057
      },
      "..."
   ]
}
```

<p>&nbsp;</p>


##### Display latest data for specified province/state
`GET /api/data/countries/{country}/{provinceState}`

Sample response:
```json
{
   "countryId":185,
   "countryName":"Canada",
   "iso2":"CA",
   "iso3":"CAN",
   "provinceState":"Ontario",
   "latitude":"51.2538",
   "longitude":"-85.3232",
   "confirmed":18574,
   "deaths":1326,
   "recovered":0,
   "active":17248
}
```

<p>&nbsp;</p>


--

#### Timelines for Countries

##### Display individual countries' timelines for confirmed/deaths/recovered cases
`GET /api/timelines/{country}`

Sample response:
```json
{
   "id":185,
   "countryName":"Canada",
   "iso2":"CA",
   "iso3":"CAN",
   "latitude":"60",
   "longitude":"-95",
   "population":37855702,
   "timelines":{
      "confirmed":{
         "2020-05-04":61957,
         "2020-05-03":60504,
         "2020-05-02":57926,
         "...": "..."
      },
      "deaths":{
         "2020-05-04":4003,
         "2020-05-03":3795,
         "2020-05-02":3684,
         "...": "..."
      },
      "recovered":{
         "2020-05-04":26030,
         "2020-05-03":24921,
         "2020-05-02":23814,
         "...": "..."
      }
   }
}
```

#### Countries' Geographical Information

##### Display all countries' geographic information
`GET /api/countries`

Sample response:
```json
[
    {
       "id":1,
       "countryName":"Afghanistan",
       "iso2":"AF",
       "iso3":"AFG",
       "latitude":"33.93911",
       "longitude":"67.709953",
       "population":38928341
    },
    {
       "id":2,
       "countryName":"Albania",
       "iso2":"AL",
       "iso3":"ALB",
       "latitude":"41.1533",
       "longitude":"20.1683",
       "population":2877800
    },
    "..."
]
```

<p>&nbsp;</p>

##### Display individual country's geographic information
`GET /api/countries/{countury}`

Sample response:
```json
{
   "id":185,
   "countryName":"Canada",
   "iso2":"CA",
   "iso3":"CAN",
   "latitude":"60",
   "longitude":"-95",
   "population":37855702
}
```

<p>&nbsp;</p>

##### Display individual country's detailed geographic information, including provinces/states
`GET /api/countries/{countury}/details`

Sample response:
```json
{
   "id":185,
   "countryName":"Canada",
   "iso2":"CA",
   "iso3":"CAN",
   "latitude":"60",
   "longitude":"-95",
   "population":37855702,
   "provincesStates":[
      {
         "provinceState":"Alberta",
         "latitude":"53.9333",
         "longitude":"-116.5765"
      },
      {
         "provinceState":"British Columbia",
         "latitude":"53.7267",
         "longitude":"-127.6476"
      },
      "..."
    ]
}
```

<p>&nbsp;</p>


##### Display individual province/state's geographic information
`GET /api/countries/{countury}/{provinceState}`

Sample response:
```json
{
   "countryId":185,
   "countryName":"Canada",
   "iso2":"CA",
   "iso3":"CAN",
   "provinceState":"Ontario",
   "latitude":"51.2538",
   "longitude":"-85.3232"
}
```











