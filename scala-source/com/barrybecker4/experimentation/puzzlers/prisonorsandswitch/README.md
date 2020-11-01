# Results
For each of the cells below I ran the simulator with 100 trials

```
Strategy           10 prisoners        20 prisoners            50 prisoners                        100 prisoners            200 prisoners              500 prisoners
FeelingLucky       113.9 [11 - 611]    3,616.8 [22 - 22,482]   496,930,263 [51 - 2,140,323,002]    [too long!]                [too long!]               [too long!]
OneCounter         117.4 [58 - 213]      460.2 [284 - 753]            2645 [1894 - 3,605]          10306 [7,556 - 12835]    41024 [34418 - 49506]     253520 [229183 - 281715]
BinaryTokens        51.89 [17 - 91]      181.9 [116 - 250]             801 [584 - 952]              2216 [1743 -2571]        6001 [4982 - 6776]        21194 [18602 - 22931]
Empirical Optimal   30.3 [13 -71]         71.7 [32 - 175]              237 [110 -390]                522 [310 - 871]         1187 [701 - 2054]          3445 [2407 - 7442]
Analytic Optimal      29                  71                           224                           518                     1175                       3396
```
The FeelingLucky strategy for 50 prisoners took about an hour to run on my computer. All the other cases took less than a second.
I am working on adding the BinaryTokens strategy - which is by far the fastest and most interesting, but will take a little longer to code.
I should also inlcude variance in the summary statistics, but I will leave that as an exercise for the reader.