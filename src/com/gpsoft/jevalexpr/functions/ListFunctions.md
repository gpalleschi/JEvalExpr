# JEvalEspr List Functions
In this file are present all functions configurated in JEvalExpr with examples.  

<hr/>

* [special characters](#specialcharacters)
* [abs](#abs)
* [acos](#acos)
* [and](#and)
* [asin](#asin)
* [atan](#atan)
* [between](#between)
* [ceil](#ceil)
* [cos](#cos)
* [decode](#decode)
* [E](#E)
* [extr_array](#extrarray)
* [in](#in)
* [initcap](#initcap)
* [instr](#instr)
* [is_date](#isdate)
* [is_null](#isnull)
* [is_number](#isnumber)
* [length](#length)
* [like](#like)
* [lower](#lower)
* [lpad](#lpad)
* [ltrim](#ltrim)
* [max](#max)
* [min](#min)
* [%](#mod)
* [!](#not)
* [nvl](#nvl)
* [or](#or)
* [PI](#PI)
* [regexp_instr](#regexpinstr)
* [regexp_like](#regexplike)
* [regexp_substr](#regexpsubstr)
* [replace](#replace)
* [reverse](#reverse)
* [round](#round)
* [rpad](#rpad)
* [rtrim](#rtrim)
* [sin](#sin)
* [substr](#substr)
* [sysdate](#sysdate)
* [tan](#tan)
* [to_char](#tochar)
* [to_date](#todate)
* [to_hhmiss](#tohhmiss)
* [to_number](#tonumber)
* [translate](#translate)
* [trim](#trim)
* [trunc](#trunc)
* [upper](#upper)
* [xor](#xor)

<hr/>
<h2 id="specialcharacters">special characters</h2>

This is a List of special characters than you can specify in your expressions :  
```
Arithmetic Operators

+ : Addition	
- : Subtraction
* : Multiplication
/ : Division
^ : Exponential
% : Module

Comparison Operators

>  : greater than 
<  : less than
>= : greater or equal than
<= : less or equal than
=  : equal than
|  : Or operator
!  : Not operator
&  : And operator

Special Operators 

|| : String Concatenation  
(  : Open Round Bracket  
)  : Close Round Brackets  

```
<hr/>
<h2 id="abs">abs</h2>
Function to calculate absolute number value.  
#####Syntax
```
abs(x)
```
Where x can be a numeric variable (int or double) or string contains a numeric value.  

#####Result
Result will be an Integer or a Double depends input type.
#####Examples
```
abs(-1)

Result will be : 1 (Integer)
```

<hr/>
<h2 id="acos">acos</h2>
Function to calculate arccosine, function works only with a numeric argument .  

#####Syntax

```
acos(x)
```
Where x is a number (Integer or Double).

#####Result
Result will be a Double.

#####Examples
```
acos(0)

Result will be : 1.5707963267948966 (Double) 
```

<hr/>
<h2 id="and">and</h2>
Logic operator and .  

#####Syntax

```
x and y
```
Where x and y can be variables of type Integer or Boolean or functions that returns Integer or Boolean.

#####Result
Result will be a Boolean value.

#####Examples
```
1 and 0

Result will be : false (Boolean) 
```
<hr/>
<h2 id="asin">asin</h2>
Function to calculate arcsine, function works only with a numeric argument .  

#####Syntax

```
asin(x)
```
Where x is a number (Integer or Double).

#####Result
Result will be a Double.

#####Examples
```
asin(0)

Result will be : 0.0 (Double) 
```
<hr/>
<h2 id="atan">atan</h2>
Function to calculate arctangent, function works only with a numeric argument .  

#####Syntax

```
atan(x)
```
Where x is a number (Integer or Double).

#####Result
Result will be a Double.

#####Examples
```
atan(180)

Result will be : 1.3386902103511544 (Double) 
```
<hr/>
<h2 id="between">between</h2>
Function to check if a value is within a range of values, function works only with numbers (Integer or double).  

#####Syntax

```
x between y and z
```
Where x is the value to check and y and z the range of values.

#####Result
Result will be a Boolean.

#####Examples
```
31 between 12 and 45

Result will be : true (Boolean) 
```
<hr/>
<h2 id="ceil">ceil</h2>
Function to rounds up and returns the smallest integer greater than or equal to a given number, function works only with numbers (Integer or Double).

#####Syntax

```
ceil(x[,y])
```
Where x is the number to round and y is number of digital to trunc.

#####Result
Result will be a number (Integer or Double).

#####Examples
```
ceil(5.345)

Result will be : 6 (Integer) 
```
<hr/>
<h2 id="cos">cos</h2>
Function to calculate cosine, function works only with a numeric argument .  

#####Syntax

```
cos(x)
```
Where x is a number (Integer or Double).

#####Result
Result will be a Double.

#####Examples
```
cos(0)

Result will be : 1.0 (Double) 
```
<hr/>
<h2 id="decode">decode</h2>
Function decode compares a value to each search value one by one. If expr is equal to a search, then returns the corresponding result. If no match is found, then returns, if present default value. If default is omitted, then returns initial value.

#####Syntax

```
decode(x,y1,z1[,y2,z2, .... yn,zn,d])
```
Where x is value to compare and y1 to yn value to compare to x and if equals, z1 to zn are values to return, d is default value if present. Variables can be Numbers (Integer or Duoble), String, Boolean or Date, it's important that x and y1..yn are of the same type, so z1...zn and d.


#####Result
Result will be a number (Double or Integer, String, Boolean or Date).

#####Examples
```
decode(12,1,'A',2,'B',12,'C','D')

Result will be : 'C' (String) 
```
<hr/>
<h2 id="E">E</h2>
E is a constant Euler's Number (2.718281828459045).

#####Syntax

```
E
```
#####Result
Value is the number Double 2.718281828459045.

#####Examples
```
E

Result will be : 2.718281828459045 (Double) 
```
<hr/>
<h2 id="extrarray">extr_array</h2>
Function extr_array is a function that allows you to extract a substring from a string formed by values ​​separated by a separator character, specifying its position. If can't extract the substring returns null.

#####Syntax

```
extr_array(x,y,z)
```
Where x is a string, y is the position and z is the separator. Position is from 1.


#####Result
Return value is a String.

#####Examples
```
extr_array('1,223,31,4987',2,',')

Result will be : '223' (String) 
```
<hr/>
<h2 id="in">in</h2>
Function in allows to check if a value in present in a group of values specified. Value and compare values can be String, number (Double or Integer), Boolean or Date.

#####Syntax

```
x in (y1,y2,.... yn)
```
Where x is the value to check, y1...yn los values to compare.


#####Result
Return value is Boolean.

#####Examples
```
'pippo' in ('pluto','paperino','minni','pluto')

Result will be : false (Boolean) 
```
<hr/>
<h2 id="initcap">initcap</h2>
Function initcap return an input String, with the first letter of each word in uppercase, all other letters in lowercase.

#####Syntax

```
initcap(x)
```
Where x is the String to transform.


#####Result
Return value is String.

#####Examples
```
initcap('john white')

Result will be : 'John White' (String) 
```
<hr/>
<h2 id="instr">instr</h2>
The instr function searchs string for substring. The search operation is defined as comparing the substring argument with substrings of string of the same length for equality until a match is found or there are no more substrings left. Each consecutive compared substring of string begins one character to the right, function returns position of substring (from 1) if founded or 0.

#####Syntax

```
instr(x,y[,z,k])
```
Where x is the input String, y the substring, z start position .


#####Result
Return value is Integer.

#####Examples
```
instr('aXaaXaaaXaaaaX','X',1,3)

Result will be : 9 (Integer) 
```
<hr/>
<h2 id="isdate">is_date</h2>
The is_date function check if a string is in date format or not, is possible specify a format pattern :

[DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html).     

If format isn't present will be assumed format 'yyyyMMdd'.  

#####Syntax

```
is_date(x,[,y])
```
Where x is the input String, y the pattern date format.    


#####Result
Return value is Boolean.

#####Examples
```
is_date('2023/07/09','yyyy/MM/dd')"

Result will be : true (Boolean) 
```
<hr/>
<h2 id="isnull">is_null</h2>
The is_null function check if a variable or a result of a nested function is null or not. Accept all types of variables.  

#####Syntax

```
is_null(x)
```
Where x is a variable or result of a nested function.    


#####Result
Return value is Boolean.

#####Examples
```
is_null(trim(' '))

Result will be : true (Boolean) 
```
<hr/>
<h2 id="isnumber">is_number</h2>
The is_number function check if an input String is a number (Integer or Double).  

#####Syntax

```
is_number(x)
```
Where x is the input String.    


#####Result
Return value is Boolean.

#####Examples
```
is_number('1a')

Result will be : false (Boolean) 
```
<hr/>
<h2 id="length">length</h2>
The length function return the length of an input String.  

#####Syntax

```
length(x)
```
Where x is the input String.    


#####Result
Return values is Integer.

#####Examples
```
length('John')

Result will be : 4 (Integer) 
```
<hr/>
<h2 id="like">like</h2>
The like function check if an input String match with another String using jolly Character '%'.  

#####Syntax

```
like(x,y)
```
Where x is the input String and y is String to match.    


#####Result
Return values is Boolean.

#####Examples
```
'pippo' like 'p%'

Result will be : true (Boolean) 
```
<hr/>
<h2 id="lower">lower</h2>
The lower function converts all upercase letters of an input String in lowercase.  

#####Syntax

```
lower(x)
```
Where x is the input String.    


#####Result
Return values is String.

#####Examples
```
lower('JOhN')

Result will be : 'john' (String) 
```
<hr/>
<h2 id="lpad">lpad</h2>
The lpad function left-pads a String with another character/String to a certain length.  

#####Syntax

```
lpad(x,y[,z])
```
Where x is the input String, y is the length to pad, and z is the character/String to pad (If isn't presente character ' ' will be used).     


#####Result
Return value is String.

#####Examples
```
lpad('12345',10,'xyz')

Result will be : 'yzxyz12345' (String) 
```
<hr/>
<h2 id="ltrim">ltrim</h2>
The ltrim function removes leading spaces from an input string.    

#####Syntax

```
ltrim(x)
```
Where x is the input String.     


#####Result
Return values is String.

#####Examples
```
ltrim('    12345 ')

Result will be : '12345 ' (String) 
```
<hr/>
<h2 id="max">max</h2>
The max function return max value from a list of values, only Strings or numbers (Integer or Double) are accepted.  

#####Syntax

```
max(x1,x2,...,xn)
```
Where x1,...,xn is the list of values to check.     

#####Result
Return value is String or Double or Integer.

#####Examples
```
max('C','D','A','L','Z','M')

Result will be : 'Z' (String) 
```
<hr/>
<h2 id="min">min</h2>
The min function return min value from a list of values, only Strings or numbers (Integer or Double) are accepted.  

#####Syntax

```
min(x1,x2,...,xn)
```
Where x1,...,xn is the list of values to check.     

#####Result
Return value is String or Double or Integer.

#####Examples
```
min(1.9,2,8,1.1,8.1,3)

Result will be : 1.1 (Double) 
```
<hr/>
<h2 id="mod">% (mod)</h2>
The mod (%) function return the remainder (modulus) of a number divided by another.  

#####Syntax

```
x % y
```
Where x is the number to divide, y is the divisor, both can be Integer or Double.     

#####Result
Return values is Double or Integer depends by divider and divisor.

#####Examples
```

77%2

Result will be : 1 (Integer) 
```
<hr/>
<h2 id="not">! (not)</h2>
The not (!) function return the inverse value of a Boolean or Integer value.  

#####Syntax

```
!x 
```
Where x is a Boolean or Integer value.     

#####Result
Return value is Boolean or Integer depends type of x.

#####Examples
```

!(1 = 1)

Result will be : false (Boolean) 
```
<hr/>
<h2 id="nvl">nvl</h2>
The nvl function check a variable or a result of a function and if is null return a value specified.  

#####Syntax

```
nvl(x,y) 
```
Where x is a variable or a result of a function, y is the value to return if is x is null.  x and y have to be of the same type (String, Boolean, Date, Integer or Double).   

#####Result
Return value depends of type of x and y.

#####Examples
```

nvl(trim(' '),'pluto')

Result will be : 'pluto' (String) 
```
<hr/>
<h2 id="or">or</h2>
The or function is logical operator to compare two conditions if one of them is true return true.  

#####Syntax

```
x or y
```
Where x is a variable/condition to compare with another variable/condition, y. x and y can be Integer or Boolean.   

#####Result
Return value is Boolean.

#####Examples
```

1 or 0

Result will be : true (Boolean) 
```
<hr/>
<h2 id="PI">PI</h2>
PI is a constant Pi greco Number (3.141592653589793).

#####Syntax

```
PI
```
#####Result
Value is the number Double 3.141592653589793.

#####Examples
```
PI

Result will be : 3.141592653589793 (Double) 
```
<hr/>
<h2 id="regexpinstr">regexp_instr</h2>
The regexp_instr function extends the functionality of the instr function by letting you search a string for a regular expression pattern. The function evaluates strings using characters as defined by the input character set. It returns an integer indicating the beginning or ending position of the matched substring, depending on the value of the return_option argument. If no match is found, then the function returns 0.  

#####Syntax

```
regexp_instr(x,y[,z,k])
```
Where x is the input String, y is the search pattern in regexp syntax, z is start position, k is number of occurences.

#####Result
Return value is Integer (position in input String from 1).

#####Examples
```
regexp_instr('xxAxxAxxxAxxxxAxxxxxAxxxxxxxA','A',10)

Result will be : 15 (Integer) 
```
<hr/>
<h2 id="regexplike">regexp_like</h2>
The regexp_like function extends the functionality of the like function by letting you search a string for a regular expression pattern. The function evaluates strings using characters as defined by the input character set. It returns a boolean flag true if match, false if not.  

#####Syntax

```
regexp_like(x,y)
```
Where x is the input String, y is the compare pattern in regexp syntax
#####Result
Return value is Boolean.

#####Examples
```
regexp_like('John','^[A-Z]o')

Result will be : true (Boolean) 
```
<hr/>
<h2 id="regexpsubstr">regexp_substr</h2>
The regexp_substr function extends the functionality of the substr function by letting you search a string for a regular expression pattern. The function evaluates strings using characters as defined by the input character set. It returns a substr if match, or a null String if not. You can specify also the start position from search (from 1) and occurences number.

#####Syntax
```
regexp_substr(x,y[,z,k])
```
Where x is the input String, y is the search pattern in regexp syntax, z is start position, k is number of occurences.

#####Result
Return value is String.

#####Examples
```
regexp_substr('xxABxxADxxxACxxxxAExxxxxBCxxxxxxBBxxxx','[A-C]{2}',14,2)

Result will be : 'BB' (String) 
```
<hr/>
<h2 id="replace">replace</h2>
The replace function replace in a string a substring/character with another substring/character. If this last substring/character is not present will be replaced with NULL.   

#####Syntax

```
replace(x,y[,z]) 
```
Where x is an input String, y is the substring/character to search, z is the substring/character to replace.   

#####Result
Return value is a String.

#####Examples
```
replace('1234a56a78','a')

Result will be : '12345678' (String) 
```
<hr/>
<h2 id="reverse">reverse</h2>
The reverse function returns a character string of exactly the same length as the argument, whose characters are exactly the same as those specified in the argument except that they are in reverse order.

#####Syntax

```
reverse(x) 
```
Where x is an input String

#####Result
Return value is a String.

#####Examples
```
reverse('AMICO')

Result will be : 'OCIMA' (String) 
```
<hr/>
<h2 id="round">round</h2>
Function round returns a number rounded to a certain number of decimal places.

#####Syntax

```
round(x[,y])
```
Where x is the number to round and y is number of digital to round.

#####Result
Result will be a number (Integer or Double).

#####Examples
```
round(6.5)

Result will be : 7 (Integer) 
```
<hr/>
<h2 id="rpad">rpad</h2>
The rpad function rigth-pads a String with another character/String to a certain length.  

#####Syntax

```
rpad(x,y[,z])
```
Where x is the input String, y is the length to pad, and z is the character/String to pad (If isn't presente character ' ' will be used).     


#####Result
Return value is String.

#####Examples
```
rpad('12345',10,'xyz')

Result will be : '12345xyzxy' (String) 
```
<hr/>
<h2 id="rtrim">rtrim</h2>
The rtrim function removes whitespace from the right side of an input string.    

#####Syntax

```
rtrim(x)
```
Where x is the input String.     


#####Result
Return values is String.

#####Examples
```
rtrim(' 12345      ')

Result will be : ' 12345' (String) 
```
<hr/>
<h2 id="sin">sin</h2>
Function to calculate sine, function works only with a numeric argument .  

#####Syntax

```
asin(x)
```
Where x is a number (Integer or Double).

#####Result
Result will be a Double.

#####Examples
```
sin(90)

Result will be : 0.0 (Double) 
```
<hr/>
<h2 id="substr">substr</h2>
Function substr extracts a substring from a string (starting at any position).  

#####Syntax

```
substr(x,y[,z])
```
Where x is an input String, y is position from (start from 1) inclusive, z is number of characters to extract, if isn't present to the end of the String.

#####Result
Result will be a String.

#####Examples
```
substr('1234567890',2,4)

Result will be : '2345' (String) 
```
<hr/>
<h2 id="sysdate">sysdate</h2>
Function sysdate return the current system date and time

#####Syntax

```
sysdate
```
#####Result
Result will be a LocalDateTime.

#####Examples
```
sysdate

Result will be : (LocalDateTime) 
```
<hr/>
<h2 id="tan">tan</h2>
Function to calculate tangent, function works only with a numeric argument .  

#####Syntax

```
tan(x)
```
Where x is a number (Integer or Double).

#####Result
Result will be a Double.

#####Examples
```
tan(180)

Result will be : 1.3386902103511544 (Double) 
```
<hr/>
<h2 id="tochar">to_char</h2>
Function to_char converts a date, a Boolean or a number (Integer or Double) to a string. 

#####Syntax

```
to_char(x[,y])
```
Where x is a number(Integer or Double), or a Boolean or a Date, if is a Date is requiered y in a format pattern :

[DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html).     

#####Result
Result will be a String.

#####Examples
```
to_char((1=1))

Result will be : 'true' (String) 

to_char(to_date('20051214','yyyyMMdd'),'yyyyMMdd')

Result will be : '20051214' (String) 

```
<hr/>
<h2 id="todate">to_date</h2>
Function to_date converts a string to a date. 

#####Syntax

```
to_date(x[,y])
```
Where x is a String, y is a format pattern (if not present format will be yyyyMMdd) in format :

[DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html).     

#####Result
Result will be a LocalDate, LocalDateTime, LocalTime depends from format.

#####Examples
```
to_date ( '2023/07/09 12:23:34' , 'yyyy/MM/dd HH:mm:ss' )

Result will be : a LocalDateTime 2023-07-09T12:23:34 

```
<hr/>
<h2 id="tohhmiss">to_hhmiss</h2>
Function to_hhmiss return a string in format HH:MI:SS from a Integer/Double value in seconds. 

#####Syntax

```
to_hhmiss(x)
```
Where x is a number (Integer/Double) represent number of seconds.

#####Result
Result will be a String.

#####Examples
```
to_hhmiss(239445)

Result will be : '66:30:45' 

```
<h2 id="translate">translate</h2>
The translate function replace in a string a substring/character with another substring/character.   

#####Syntax

```
traslate(x,y,z) 
```
Where x is an input String, y is the substring/character to search, z is the substring/character to replace.   

#####Result
Return value is a String.

#####Examples
```
translate('xxxxYxxxYxxxx','Y','ZX')

Result will be : 'xxxxZXxxxZXxxxx' (String) 
```
<hr/>
<h2 id="trim">trim</h2>
The trim function removes leading spaces and spaces from the right side from an input string.    

#####Syntax

```
trim(x)
```
Where x is the input String.     


#####Result
Return values is String.

#####Examples
```
ltrim('    12345 ')

Result will be : '12345' (String) 
```
<hr/>
<h2 id="trunc">trunc</h2>
Function trunc returns a number truncated to a certain number of decimal places. 

#####Syntax

```
trunc(x[,y])
```
Where x is the number to round and y is number of digital to trunc, if is not present will be truncated all decimal digits.

#####Result
Result will be a number (Integer or Double).

#####Examples
```
trunc(710.1234,2)

Result will be : 710.12 (Double) 
```
<hr/>
<h2 id="upper">upper</h2>
The upper function converts all lowercase letters of an input String in uppercase.  

#####Syntax

```
upper(x)
```
Where x is the input String.    

#####Result
Return values is String.

#####Examples
```
upper('minUs')

Result will be : 'MINUS' (String) 
```
<hr/>
<h2 id="xor">xor</h2>
The xor function is a logical operator xor where if both condition are equals return false otherwise true.  

#####Syntax

```
x xor y
```
Where x and y are two conditions/values (Boolean or Integer).    

#####Result
Return values is String.

#####Examples
```
(1=1) xor (1=1)

Result will be : false (Boolean) 
```



