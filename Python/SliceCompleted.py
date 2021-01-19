'''Slice'''

a = [1,12,23,34,45,56, 3, 5, 36, 75, 81, 2]
b = [2, 4, 6, 7, 8, 57 ,89, 9]

'''Print out the 2nd to the 5th elements of list "a"'''

print(a[1:5])

'''Create a list that contains the 4th to the 8th element of "a" and the 1st to the 3rd element of "b"'''

c = a[3:8] + b[0:3]
print(c)

'''Append "a" with the 3rd to the 5th elements of "c"'''

a += c[2:5]
print(a)

'''Create a list that contains the 1st to the 3rd element of "a", the 4th to the 5th element of "b", and the 6th to the 7th element of "c"'''

d = a[0:3] + b[3:5] + c[5:7]
print(d)

'''Append "c" with the last 4 elements of "d"'''

c += d[len(d) - 4:len(d)]
print(c)

'''Set list "a" to the first 3 values of d'''

a = d[0:3]
print(a)
