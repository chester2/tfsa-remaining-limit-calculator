# TFSA Remaining Limit Calculator

Calculator for current year's remaining TFSA contribution limit. Fetches yearly contribution limits from [this service](https://github.com/chester2/tfsa-limits).

See the API documentation [here](https://app.swaggerhub.com/apis/cwxw/tfsa-remaining-limit-calculator).

## Example

Let the following request body be given:

```
{
    "currentYear": 2018,
    "residencyYears": [2011, 2012, 2013, 2014, 2018, 2019],
    "transactions": [
        { "year": 2011, "amount": 4000 },
        { "year": 2013, "amount": 3000 },
        { "year": 2014, "amount": 1000 },
        { "year": 2014, "amount": -2000 },
        { "year": 2015, "amount": 3000 },
        { "year": 2018, "amount": -2000 },
    ]
}
```

This request models someone who is both over 18 and a Canadian resident during the years 2011-2014 and 2018-2019.

| Item | Description | Value |
| - | - | - |
| Total accumulated limit | Sum of TFSA limits given for residency years up to current year | 26,500 |
| Total contributions | Sum of positive transactions that occurred in residency years up to current year | 8,000 |
| Total withdrawals | Sum of negative transactions that occurred in residency years before current year | 2,000 |
| Remaining limit | - | 20,500 |
