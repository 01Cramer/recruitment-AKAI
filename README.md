# recruitment-AKAI
## Recruitment tasks made in Java for AKAI backend section. AKAI stands for science club at Poznan University Of Technology interested in web/mobile applications.

## Task 1. book-api
The entire API contains only one endpoint: https://akai-recruitment-api.prod.ang3r.pl/books
This endpoint returns a list of books, each containing the following information:
- id
- title
- author
- rating

Your task is to:
1. Create an appropriate class for storing book information.
2. Parse the data provided by the endpoint. To make this task easier,
   three popular libraries for parsing JSON to Java objects have been included
   in the project - Gson, Org.Json, Jackson. You can use any of them.
3. After parsing the JSON into Java objects, enhance the program with a function
   that prints the top 3 authors with the highest average ratings. For example,
   if person X is the author of book A with a rating of 9 and book B with a rating of 8,
   the information should be displayed as: X - 8.5

### Notes to my solution:
I know that the good practice is to code classes in different files, but I decided that for this simple class, it is not necessary, and coding it in one file will make it easier for the recruiter to review. 

## Task 2. word-count
Your task is to print on the console the three most frequently occurring words in the 'sentences' array along with their respective frequencies.
Sample output:
1. "mam" - 12
2. "tak" - 5
3. "z" - 2

'Sentences' array consist of common Polish proverbs.
