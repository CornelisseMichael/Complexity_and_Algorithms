# C-A-Replacement-Selection
Assignment 2: Applications of a heap
2.1 – Replacement Selection
When sorting external files it is important to achieve each possible optimization of an
algorithm. One of the possibilities is to optimize the starting situation by providing as long as
possible sorted parts (runs) in the to be sorted file.
One way to lenghten the initial runs in a file has been discussed in the lectures, i.e. how this
can be realised using a dynamic heap with dead space, a so called DynDSHeap.
Design and implement a class DynDSHeap, an efficient data structure for such a special kind
of heap, including the corresponding methods.
Then write a program, based upon the above description, that will read from an (input)file
with N random numbers, using an DynDSHeap with heapsize H, and producing the
lengthened runs on an (output)file.
Choose several different combinations of values for N and H. Use a suitable testset when
testing your algorithm, such that all special situations are covered.
Without using the replacement selection algorithm, an input file with N random numbers and
a heapsize H would give you about N/H runs with an average length H. Thanks to this
algorithm it is expected that the number of runs will be halved, so the run length will be 2*H.
Compare these expectations with your actual results.
