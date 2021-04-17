### Identify a couple of examples on the use of AssertJ expressive methods chaining.
	AssertJmethod chaining may be used in order to test HTTP request, namely in order to know the response status on the Request, to see if the code return is as expected, other objective is to actually check if the HTTP request presents the expected data. Another use may be to test the repository and if it is accessing the correct data in each request.

### Identify an example in which you mock the behavior of the repository (and avoid involving a database). 
	Since database information changes frequently and the objective is to check if methods and classes were developed according to the tests we may not know if some data is or is not present inside the database nor in the form desired, so in order to make sure that the expected result (the result to which we compare in the tests) is correct we mock the repository.

### What is the difference between standard @Mock and @MockBean?
	For starters, @Mock is an Annotiation that comes from mockito whereas @MockBean is a Spring annotation. Besides this initial difference @Mock is only capable of mocking an entity by itself and lacks the context of the other entities and, in some cases, there is no problem since the test is supposed to be focused on a certain entity alone. @MockBean is a spring testing annotation where besides mocking the entity it gives some context relative to other sections of the code and may be complemented by some other annotations such as @WebMvcTest and @DataJpaTest.

### What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
	The mentioned file starts a in-memory database when a test needs to make tests relative to data persistance.
