Code Conventions and Style for Robot19-VSCode

Indent: 2 or 4 spaces

For Loops:

    for (int [var: i, x, j, k, l, m, n] = [some number]; [var] < [other var (usually array.length)]; i++) {
        [Your code here];
    }

If Statements:

    Single:
    if (Param) [Your code here];

    Multi:
    if (Param) {
        [Your code here];
    }
    else if (Param) {
        [Your code here];
    }
    else {
        [Your code here];
    }

    OR

    if (Param) {
        [Your code here];
    } else if (Param) {
        [Your code here];
    } else {
        [Your code here];
    }

While Loops:

    while(Param) {
        [Your code here];
    }

Class/Methods:

    [scope] [return] [Name](Params) {
        [Your code here];
    }

Math and variable manipulation:

    Good:
    double speedVal = (slowVal * input) + 5;

    Bad:
    double speedVal=(slowVal*input)+5;

Booleans:

    for boolean bool = true;
    Good:
    if (bool) doThis();

    Bad:
    if (bool == true) doThis();

    for boolean bool = false;
    Good:
    if (!bool) doThis;

    Bad:
    if (bool == false) doThis();

Strings:

    Good:
    String str = ("" + someVar + otherVar);

    Bad:
    String str=""+someVar+otherVar;
