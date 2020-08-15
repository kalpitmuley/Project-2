# Project-2

A spring boot app which interacts with MongoDB as no-SQL database

My Intentions for doing this projects were:

Create a spring boot app which interacts with any no-sql database (mongodb or any other database that you are comfortable with) and perform all the below mentioned tasks in it. 

Book
{
	Int id,
	String name,
	String authorName
	int cost,

}

Task 1 : Create GET API which will return all the books which have authorName passed int the API request i.e, you have to take authorName in the request Param and find all the books written by that particular author

Task 2 : Create 2 POST APIs which will take in a list of books in the request body (not a single book) and then insert those in the mongo collection. 
But the catch here is, in 1 API you have to insert the books parallely using multiple threads
And in the second API you have to insert using repsoitory’s saveAll function without creating your threads

What is required in this task ?
Both APIs should be working correctly (i.e all the entries should be inserted in collection) and
You have to compare which API performs better the multithreaded one or API which is calling saveAll function

You have to write a small description about the difference b/w these 2, your personal views which one to use and why ?

Since this is a theoretical ques, you can write anything

The theoretical answer is: https://docs.google.com/document/d/1ehIYyIfObhcby7H4K_KXXab7cdggPggs5461nFEc5oE/edit?usp=sharing
