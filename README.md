# NumberUtil
convert number to persian word

## usage
pass number in string and return the word equivalent to that number in Persian

```
//in kotlin 

//یک میلیون و دویست و پنجاه هزار
println(NumberUtil.toPersianWord("1250000"))

//ممیز یکصد و بیست و سه هزارم
println(NumberUtil.toPersianWord(".123"))

//یکصد و دوازده ممیز یکصد و سیزده هزارم
println(NumberUtil.toPersianWord("112.113"))

//یکصد و بیست
println(NumberUtil.toPersianWord("120.0"))

//نود و نه
println(NumberUtil.toPersianWord("99"))

//یکصد
println(NumberUtil.toPersianWord("100"))



// in java jsut add `Companion` before `toPersionWord` function
NumberUtil.Companion.toPersianWord("1234567889.0123456789")
//یک میلیارد و دویست و سی و چهار میلیون و پانصد و شصت و هفت هزار و هشتصد و هشتاد و نه ممیز دوازده میلیون و سیصد و چهل و پنج هزار و هفتصد و هشتاد و نه میلیاردم

```


## feature
Convert integers with a maximum length of 105 and decimal numbers with a maximum length of 11 into Persian words
If you use a number longer than that defined throw `Exception`

## feature in persian(ویژگی ها به پارسی)
تبدیل اعداد صحیح با طول حداکثر 105 و اعداد اعشاری با طول حداکثر 11  به کلمه فارسی 

اگر شما از یک عدد بزرگتر از حداکثرهای تعریف شده استفاده کنید خطای

`Exception`

اتفاق می افتد

## test
A real programmer uses `in production` test method, which causes more excitement and adrenaline release, resulting in physical health and mental damage.

Or

go one step further and do not test your program at all
