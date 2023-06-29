# canoe-paths

Given a file with a matrix that contains the cost for each post along a journey, determine the cheapest path from the start to the end. The matrix should be in the following format:
    The first line should contain a single integer representing the number of posts.
    The remaining lines should contain the cost matrix for the trading posts. Each line should correspond to a row in the matrix, and each element in the line should represent the cost of traveling from one trading post to another.
    eg: 
    3
0 2 3 4 5
0 0 1 2 3
0 0 0 0 0

The algorithm finds teh shortest path in a weighted graph, since each post has a cost associated with it that represents the weight on the graph. This uses a dynamic programming approach 
