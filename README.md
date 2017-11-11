# MDA-analysis
Minimal distance analysis for mitochondrial genome analysis in JAVA</br>
Command line aplication for MDA analysis </br>
File used for analysis: </br>
```
5pBP.txt: 5' breakpoints
3G33Nshuman: Output of midpoints from Dong script 3G33N- 
2G33N-human: Output of midpoints from Dong script 2G33N- 
G4hunter: Output of midpoints from G4Hunter by Joao with 3G33N (from email)
```
Sample output:</br>
```
Please input the textfile for listed deletion break points here:
5pBP.txt
Please input the textfile for listed QFP midpoints here:
G4Hunter33N.txt
Input your read window here
200
Minimal distance average at position 0 is 99.66803278688525
your p-value is 0.927710843373494

C:\Users\Yuefu\Documents\Kaufman Lab\MDA analysis>java MDA
Please input the textfile for listed deletion break points here:
5pBP.txt
Please input the textfile for listed QFP midpoints here:
3G33Nshuman.txt
Input your read window here
200
Minimal distance average at position 0 is 13.61111111111111
your p-value is 0.0

C:\Users\Yuefu\Documents\Kaufman Lab\MDA analysis>java MDA
Please input the textfile for listed deletion break points here:
5pBP.txt
Please input the textfile for listed QFP midpoints here:
2G33N-human.txt
Input your read window here
200
Minimal distance average at position 0 is 78.25581395348837
your p-value is 0.7108433734939759
```
