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
<h5>Syntax</h5> 

```  
abs(x)
```  
Where x can be a numeric variable (int or double) or string contains a numeric value.  

<h5>Result</h5>   
Result will be an Integer or a Double depends input type.
<h5>Examples</h5>   
```  
abs(-1)

Result will be : 1 (Integer)
```  

<hr/>
<h2 id="acos">acos</h2>
Function to calculate arccosine, function works only with a numeric argument .  

<h5>Syntax</h5> 

```  
acos(x)
```  
Where x is a number (Integer or Double).

<h5>Result</h5>   
Result will be a Double.

<h5>Examples</h5>   
```  
acos(0)

Result will be : 1.5707963267948966 (Double) 
```  

<hr/>
<h2 id="and">and</h2>
Logic operator and .  

<h5>Syntax</h5> 

```  
x and y
```  
Where x and y can be variables of type Integer or Boolean or functions that returns Integer or Boolean.

<h5>Result</h5>   
Result will be a Boolean value.

<h5>Examples</h5>   
```  
1 and 0

Result will be : false (Boolean) 
```  
<hr/>
<h2 id="asin">asin</h2>
Function to calculate arcsine, function works only with a numeric argument .  

<h5>Syntax</h5> 

```  
asin(x)
```  
Where x is a number (Integer or Double).

<h5>Result</h5>   
Result will be a Double.

<h5>Examples</h5>   
```  
asin(0)

Result will be : 0.0 (Double) 
```  
<hr/>
<h2 id="atan">atan</h2>
Function to calculate arctangent, function works only with a numeric argument .  

<h5>Syntax</h5> 

```  
atan(x)
```  
Where x is a number (Integer or Double).

<h5>Result</h5>   
Result will be a Double.

<h5>Examples</h5>   
```  
atan(180)

Result will be : 1.3386902103511544 (Double) 
```  
<hr/>
<h2 id="between">between</h2>
Function to check if a value is within a range of values, function works only with numbers (Integer or double).  

<h5>Syntax</h5> 

```  
x between y and z
```  
Where x is the value to check and y and z the range of values.

<h5>Result</h5>   
Result will be a Boolean.

<h5>Examples</h5>   
```  
31 between 12 and 45

Result will be : true (Boolean) 
```  
<hr/>
<h2 id="ceil">ceil</h2>
Function to rounds up and returns the smallest integer greater than or equal to a given number, function works only with numbers (Integer or Double).

<h5>Syntax</h5> 

```  
ceil(x[,y])
```  
Where x is the number to round and y is number of digital to trunc.

<h5>Result</h5>   
Result will be a number (Integer or Double).

<h5>Examples</h5>   
```  
ceil(5.345)

Result will be : 6 (Integer) 
```  
<hr/>
<h2 id="cos">cos</h2>
Function to calculate cosine, function works only with a numeric argument .  

<h5>Syntax</h5> 

```  
cos(x)
```  
Where x is a number (Integer or Double).

<h5>Result</h5>   
Result will be a Double.

<h5>Examples</h5>   
```  
cos(0)

Result will be : 1.0 (Double) 
```  
<hr/>
<h2 id="decode">decode</h2>
Function decode compares a value to each search value one by one. If expr is equal to a search, then returns the corresponding result. If no match is found, then returns, if present default value. If default is omitted, then returns initial value.

<h5>Syntax</h5> 

```  
decode(x,y1,z1[,y2,z2, .... yn,zn,d])
```  
Where x is value to compare and y1 to yn value to compare to x and if equals, z1 to zn are values to return, d is default value if present. Variables can be Numbers (Integer or Duoble), String, Boolean or Date, it's important that x and y1..yn are of the same type, so z1...zn and d.


<h5>Result</h5>   
Result will be a number (Double or Integer, String, Boolean or Date).

<h5>Examples</h5>   
```  
decode(12,1,'A',2,'B',12,'C','D')

Result will be : 'C' (String) 
```  
<hr/>
<h2 id="E">E</h2>
E is a constant Euler's Number (2.718281828459045).

<h5>Syntax</h5> 

```  
E
```  
<h5>Result</h5>   
Value is the number Double 2.718281828459045.

<h5>Examples</h5>   
```  
E

Result will be : 2.718281828459045 (Double) 
```  
<hr/>
<h2 id="extrarray">extr_array</h2>
Function extr_array is a function that allows you to extract a substring from a string formed by values ​​separated by a separator character, specifying its position. If can't extract the substring returns null.

<h5>Syntax</h5> 

```  
extr_array(x,y,z)
```  
Where x is a string, y is the position and z is the separator. Position is from 1.


<h5>Result</h5>   
Return value is a String.

<h5>Examples</h5>   
```  
extr_array('1,223,31,4987',2,',')

Result will be : '223' (String) 
```  
<hr/>
<h2 id="in">in</h2>
Function in allows to check if a value in present in a group of values specified. Value and compare values can be String, number (Double or Integer), Boolean or Date.

<h5>Syntax</h5> 

```  
x in (y1,y2,.... yn)
```  
Where x is the value to check, y1...yn los values to compare.


<h5>Result</h5>   
Return value is Boolean.

<h5>Examples</h5>   
```  
'pippo' in ('pluto','paperino','minni','pluto')

Result will be : false (Boolean) 
```  
<hr/>
<h2 id="initcap">initcap</h2>
Function initcap return an input String, with the first letter of each word in uppercase, all other letters in lowercase.

<h5>Syntax</h5> 

```  
initcap(x)
```  
Where x is the String to transform.


<h5>Result</h5>   
Return value is String.

<h5>Examples</h5>   
```  
initcap('john white')

Result will be : 'John White' (String) 
```  
<hr/>
<h2 id="instr">instr</h2>
The instr function searchs string for substring. The search operation is defined as comparing the substring argument with substrings of string of the same length for equality until a match is found or there are no more substrings left. Each consecutive compared substring of string begins one character to the right, function returns position of substring (from 1) if founded or 0.

<h5>Syntax</h5> 

```  
instr(x,y[,z,k])
```  
Where x is the input String, y the substring, z start position .


<h5>Result</h5>   
Return value is Integer.

<h5>Examples</h5>   
```  
instr('aXaaXaaaXaaaaX','X',1,3)

Result will be : 9 (Integer) 
```  
<hr/>
<h2 id="isdate">is_date</h2>
The is_date function check if a string is in date format or not, is possible specify a format pattern :

[DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html).     

If format isn't present will be assumed format 'yyyyMMdd'.  

<h5>Syntax</h5> 

```  
is_date(x,[,y])
```  
Where x is the input String, y the pattern date format.    


<h5>Result</h5>   
Return value is Boolean.

<h5>Examples</h5>   
```  
is_date('2023/07/09','yyyy/MM/dd')"

Result will be : true (Boolean) 
```  
<hr/>
<h2 id="isnull">is_null</h2>
The is_null function check if a variable or a result of a nested function is null or not. Accept all types of variables.  

<h5>Syntax</h5> 

```  
is_null(x)
```  
Where x is a variable or result of a nested function.    


<h5>Result</h5>   
Return value is Boolean.

<h5>Examples</h5>   
```  
is_null(trim(' '))

Result will be : true (Boolean) 
```  
<hr/>
<h2 id="isnumber">is_number</h2>
The is_number function check if an input String is a number (Integer or Double).  

<h5>Syntax</h5> 

```  
is_number(x)
```  
Where x is the input String.    


<h5>Result</h5>   
Return value is Boolean.

<h5>Examples</h5>   
```  
is_number('1a')

Result will be : false (Boolean) 
```  
<hr/>
<h2 id="length">length</h2>
The length function return the length of an input String.  

<h5>Syntax</h5> 

```  
length(x)
```  
Where x is the input String.    


<h5>Result</h5>   
Return values is Integer.

<h5>Examples</h5>   
```  
length('John')

Result will be : 4 (Integer) 
```  
<hr/>
<h2 id="like">like</h2>
The like function check if an input String match with another String using jolly Character '%'.  

<h5>Syntax</h5> 

```  
like(x,y)
```  
Where x is the input String and y is String to match.    


<h5>Result</h5>   
Return values is Boolean.

<h5>Examples</h5>   
```  
'pippo' like 'p%'

Result will be : true (Boolean) 
```  
<hr/>
<h2 id="lower">lower</h2>
The lower function converts all upercase letters of an input String in lowercase.  

<h5>Syntax</h5> 

```  
lower(x)
```  
Where x is the input String.    


<h5>Result</h5>   
Return values is String.

<h5>Examples</h5>   
```  
lower('JOhN')

Result will be : 'john' (String) 
```  
<hr/>
<h2 id="lpad">lpad</h2>
The lpad function left-pads a String with another character/String to a certain length.  

<h5>Syntax</h5> 

```  
lpad(x,y[,z])
```  
Where x is the input String, y is the length to pad, and z is the character/String to pad (If isn't presente character ' ' will be used).     


<h5>Result</h5>   
Return value is String.

<h5>Examples</h5>   
```  
lpad('12345',10,'xyz')

Result will be : 'yzxyz12345' (String) 
```  
<hr/>
<h2 id="ltrim">ltrim</h2>
The ltrim function removes leading spaces from an input string.    

<h5>Syntax</h5> 

```  
ltrim(x)
```  
Where x is the input String.     


<h5>Result</h5>   
Return values is String.

<h5>Examples</h5>   
```  
ltrim('    12345 ')

Result will be : '12345 ' (String) 
```  
<hr/>
<h2 id="max">max</h2>
The max function return max value from a list of values, only Strings or numbers (Integer or Double) are accepted.  

<h5>Syntax</h5> 

```  
max(x1,x2,...,xn)
```  
Where x1,...,xn is the list of values to check.     

<h5>Result</h5>   
Return value is String or Double or Integer.

<h5>Examples</h5>   
```  
max('C','D','A','L','Z','M')

Result will be : 'Z' (String) 
```  
<hr/>
<h2 id="min">min</h2>
The min function return min value from a list of values, only Strings or numbers (Integer or Double) are accepted.  

<h5>Syntax</h5> 

```  
min(x1,x2,...,xn)
```  
Where x1,...,xn is the list of values to check.     

<h5>Result</h5>   
Return value is String or Double or Integer.

<h5>Examples</h5>   
```  
min(1.9,2,8,1.1,8.1,3)

Result will be : 1.1 (Double) 
```  
<hr/>
<h2 id="mod">% (mod)</h2>
The mod (%) function return the remainder (modulus) of a number divided by another.  

<h5>Syntax</h5> 

```  
x % y
```  
Where x is the number to divide, y is the divisor, both can be Integer or Double.     

<h5>Result</h5>   
Return values is Double or Integer depends by divider and divisor.

<h5>Examples</h5>   
```  

77%2

Result will be : 1 (Integer) 
```  
<hr/>
<h2 id="not">! (not)</h2>
The not (!) function return the inverse value of a Boolean or Integer value.  

<h5>Syntax</h5> 

```  
!x 
```  
Where x is a Boolean or Integer value.     

<h5>Result</h5>   
Return value is Boolean or Integer depends type of x.

<h5>Examples</h5>   
```  

!(1 = 1)

Result will be : false (Boolean) 
```  
<hr/>
<h2 id="nvl">nvl</h2>
The nvl function check a variable or a result of a function and if is null return a value specified.  

<h5>Syntax</h5> 

```  
nvl(x,y) 
```  
Where x is a variable or a result of a function, y is the value to return if is x is null.  x and y have to be of the same type (String, Boolean, Date, Integer or Double).   

<h5>Result</h5>   
Return value depends of type of x and y.

<h5>Examples</h5>   
```  

nvl(trim(' '),'pluto')

Result will be : 'pluto' (String) 
```  
<hr/>
<h2 id="or">or</h2>
The or function is logical operator to compare two conditions if one of them is true return true.  

<h5>Syntax</h5> 

```  
x or y
```  
Where x is a variable/condition to compare with another variable/condition, y. x and y can be Integer or Boolean.   

<h5>Result</h5>   
Return value is Boolean.

<h5>Examples</h5>   
```  

1 or 0

Result will be : true (Boolean) 
```  
<hr/>
<h2 id="PI">PI</h2>
PI is a constant Pi greco Number (3.141592653589793).

<h5>Syntax</h5> 

```  
PI
```  
<h5>Result</h5>   
Value is the number Double 3.141592653589793.

<h5>Examples</h5>   
```  
PI

Result will be : 3.141592653589793 (Double) 
```  
<hr/>
<h2 id="regexpinstr">regexp_instr</h2>
The regexp_instr function extends the functionality of the instr function by letting you search a string for a regular expression pattern. The function evaluates strings using characters as defined by the input character set. It returns an integer indicating the beginning or ending position of the matched substring, depending on the value of the return_option argument. If no match is found, then the function returns 0.  

<h5>Syntax</h5> 

```  
regexp_instr(x,y[,z,k])
```  
Where x is the input String, y is the search pattern in regexp syntax, z is start position, k is number of occurences.

<h5>Result</h5>   
Return value is Integer (position in input String from 1).

<h5>Examples</h5>   
```  
regexp_instr('xxAxxAxxxAxxxxAxxxxxAxxxxxxxA','A',10)

Result will be : 15 (Integer) 
```  
<hr/>
<h2 id="regexplike">regexp_like</h2>
The regexp_like function extends the functionality of the like function by letting you search a string for a regular expression pattern. The function evaluates strings using characters as defined by the input character set. It returns a boolean flag true if match, false if not.  

<h5>Syntax</h5> 

```  
regexp_like(x,y)
```  
Where x is the input String, y is the compare pattern in regexp syntax
<h5>Result</h5>   
Return value is Boolean.

<h5>Examples</h5>   
```  
regexp_like('John','^[A-Z]o')

Result will be : true (Boolean) 
```  
<hr/>
<h2 id="regexpsubstr">regexp_substr</h2>
The regexp_substr function extends the functionality of the substr function by letting you search a string for a regular expression pattern. The function evaluates strings using characters as defined by the input character set. It returns a substr if match, or a null String if not. You can specify also the start position from search (from 1) and occurences number.

<h5>Syntax</h5> 
```  
regexp_substr(x,y[,z,k])
```  
Where x is the input String, y is the search pattern in regexp syntax, z is start position, k is number of occurences.

<h5>Result</h5>   
Return value is String.

<h5>Examples</h5>   
```  
regexp_substr('xxABxxADxxxACxxxxAExxxxxBCxxxxxxBBxxxx','[A-C]{2}',14,2)

Result will be : 'BB' (String) 
```  
<hr/>
<h2 id="replace">replace</h2>
The replace function replace in a string a substring/character with another substring/character. If this last substring/character is not present will be replaced with NULL.   

<h5>Syntax</h5> 

```  
replace(x,y[,z]) 
```  
Where x is an input String, y is the substring/character to search, z is the substring/character to replace.   

<h5>Result</h5>   
Return value is a String.

<h5>Examples</h5>   
```  
replace('1234a56a78','a')

Result will be : '12345678' (String) 
```  
<hr/>
<h2 id="reverse">reverse</h2>
The reverse function returns a character string of exactly the same length as the argument, whose characters are exactly the same as those specified in the argument except that they are in reverse order.

<h5>Syntax</h5> 

```  
reverse(x) 
```  
Where x is an input String

<h5>Result</h5>   
Return value is a String.

<h5>Examples</h5>   
```  
reverse('AMICO')

Result will be : 'OCIMA' (String) 
```  
<hr/>
<h2 id="round">round</h2>
Function round returns a number rounded to a certain number of decimal places.

<h5>Syntax</h5> 

```  
round(x[,y])
```  
Where x is the number to round and y is number of digital to round.

<h5>Result</h5>   
Result will be a number (Integer or Double).

<h5>Examples</h5>   
```  
round(6.5)

Result will be : 7 (Integer) 
```  
<hr/>
<h2 id="rpad">rpad</h2>
The rpad function rigth-pads a String with another character/String to a certain length.  

<h5>Syntax</h5> 

```  
rpad(x,y[,z])
```  
Where x is the input String, y is the length to pad, and z is the character/String to pad (If isn't presente character ' ' will be used).     


<h5>Result</h5>   
Return value is String.

<h5>Examples</h5>   
```  
rpad('12345',10,'xyz')

Result will be : '12345xyzxy' (String) 
```  
<hr/>
<h2 id="rtrim">rtrim</h2>
The rtrim function removes whitespace from the right side of an input string.    

<h5>Syntax</h5> 

```  
rtrim(x)
```  
Where x is the input String.     


<h5>Result</h5>   
Return values is String.

<h5>Examples</h5>   
```  
rtrim(' 12345      ')

Result will be : ' 12345' (String) 
```  
<hr/>
<h2 id="sin">sin</h2>
Function to calculate sine, function works only with a numeric argument .  

<h5>Syntax</h5> 

```  
asin(x)
```  
Where x is a number (Integer or Double).

<h5>Result</h5>   
Result will be a Double.

<h5>Examples</h5>   
```  
sin(90)

Result will be : 0.0 (Double) 
```  
<hr/>
<h2 id="substr">substr</h2>
Function substr extracts a substring from a string (starting at any position).  

<h5>Syntax</h5> 

```  
substr(x,y[,z])
```  
Where x is an input String, y is position from (start from 1) inclusive, z is number of characters to extract, if isn't present to the end of the String.

<h5>Result</h5>   
Result will be a String.

<h5>Examples</h5>   
```  
substr('1234567890',2,4)

Result will be : '2345' (String) 
```  
<hr/>
<h2 id="sysdate">sysdate</h2>
Function sysdate return the current system date and time

<h5>Syntax</h5> 

```  
sysdate
```  
<h5>Result</h5>   
Result will be a LocalDateTime.

<h5>Examples</h5>   
```  
sysdate

Result will be : (LocalDateTime) 
```  
<hr/>
<h2 id="tan">tan</h2>
Function to calculate tangent, function works only with a numeric argument .  

<h5>Syntax</h5> 

```  
tan(x)
```  
Where x is a number (Integer or Double).

<h5>Result</h5>   
Result will be a Double.

<h5>Examples</h5>   
```  
tan(180)

Result will be : 1.3386902103511544 (Double) 
```  
<hr/>
<h2 id="tochar">to_char</h2>
Function to_char converts a date, a Boolean or a number (Integer or Double) to a string. 

<h5>Syntax</h5> 

```  
to_char(x[,y])
```  
Where x is a number(Integer or Double), or a Boolean or a Date, if is a Date is requiered y in a format pattern :

[DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html).     

<h5>Result</h5>   
Result will be a String.

<h5>Examples</h5>   
```  
to_char((1=1))

Result will be : 'true' (String) 

to_char(to_date('20051214','yyyyMMdd'),'yyyyMMdd')

Result will be : '20051214' (String) 

```  
<hr/>
<h2 id="todate">to_date</h2>
Function to_date converts a string to a date. 

<h5>Syntax</h5> 

```  
to_date(x[,y])
```  
Where x is a String, y is a format pattern (if not present format will be yyyyMMdd) in format :

[DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html).     

<h5>Result</h5>   
Result will be a LocalDate, LocalDateTime, LocalTime depends from format.

<h5>Examples</h5>   
```  
to_date ( '2023/07/09 12:23:34' , 'yyyy/MM/dd HH:mm:ss' )

Result will be : a LocalDateTime 2023-07-09T12:23:34 

```  
<hr/>
<h2 id="tohhmiss">to_hhmiss</h2>
Function to_hhmiss return a string in format HH:MI:SS from a Integer/Double value in seconds. 

<h5>Syntax</h5> 

```  
to_hhmiss(x)
```  
Where x is a number (Integer/Double) represent number of seconds.

<h5>Result</h5>   
Result will be a String.

<h5>Examples</h5>   
```  
to_hhmiss(239445)

Result will be : '66:30:45' 

```  
<h2 id="translate">translate</h2>
The translate function replace in a string a substring/character with another substring/character.   

<h5>Syntax</h5> 

```  
traslate(x,y,z) 
```  
Where x is an input String, y is the substring/character to search, z is the substring/character to replace.   

<h5>Result</h5>   
Return value is a String.

<h5>Examples</h5>   
```  
translate('xxxxYxxxYxxxx','Y','ZX')

Result will be : 'xxxxZXxxxZXxxxx' (String) 
```  
<hr/>
<h2 id="trim">trim</h2>
The trim function removes leading spaces and spaces from the right side from an input string.    

<h5>Syntax</h5> 

```  
trim(x)
```  
Where x is the input String.     


<h5>Result</h5>   
Return values is String.

<h5>Examples</h5>   
```  
ltrim('    12345 ')

Result will be : '12345' (String) 
```  
<hr/>
<h2 id="trunc">trunc</h2>
Function trunc returns a number truncated to a certain number of decimal places. 

<h5>Syntax</h5> 

```  
trunc(x[,y])
```  
Where x is the number to round and y is number of digital to trunc, if is not present will be truncated all decimal digits.

<h5>Result</h5>   
Result will be a number (Integer or Double).

<h5>Examples</h5>   
```  
trunc(710.1234,2)

Result will be : 710.12 (Double) 
```  
<hr/>
<h2 id="upper">upper</h2>
The upper function converts all lowercase letters of an input String in uppercase.  

<h5>Syntax</h5> 

```  
upper(x)
```  
Where x is the input String.    

<h5>Result</h5>   
Return values is String.

<h5>Examples</h5>   
```  
upper('minUs')

Result will be : 'MINUS' (String) 
```  
<hr/>
<h2 id="xor">xor</h2>
The xor function is a logical operator xor where if both condition are equals return false otherwise true.  

<h5>Syntax</h5> 

```  
x xor y
```  
Where x and y are two conditions/values (Boolean or Integer).    

<h5>Result</h5>   
Return values is String.

<h5>Examples</h5>   
```  
(1=1) xor (1=1)

Result will be : false (Boolean) 
```  



