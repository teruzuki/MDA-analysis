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
It seems like G4hunter output has no relation at all with the deletion breakpoint.</br>
Note: setting the deletion frame shift under 100 will cost a significant amout of time for the program to run. 
```
Please input the textfile for listed deletion break points here:
5pBP.txt
Please input the textfile for listed QFP midpoints here:
2G33N-human.txt
Input your deletion frame shift here
10
Minimal distance average at position 0 is 78.25581395348837
your p-value is 0.7042848521424261

C:\Users\Yuefu\Documents\Kaufman Lab\MDA analysis>java MDA
Please input the textfile for listed deletion break points here:
5pBP.txt
Please input the textfile for listed QFP midpoints here:
3G33Nshuman.txt
Input your deletion frame shift here
10
Minimal distance average at position 0 is 13.61111111111111
your p-value is 0.0030175015087507543

C:\Users\Yuefu\Documents\Kaufman Lab\MDA analysis>java MDA
Please input the textfile for listed deletion break points here:
5pBP.txt
Please input the textfile for listed QFP midpoints here:
G4Hunter33N.txt
Input your deletion frame shift here
10
Minimal distance average at position 0 is 99.66803278688525
your p-value is 0.9203379601689801
```
