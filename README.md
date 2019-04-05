# cat_facts_app

In this app, I used Clean Architecture in order to make the code easier to read and test, and as I had some problems using Cat Fact API (https://cat-fact.herokuapp.com),
due to a bug in the endpoint (which I already reported and the developer is fixing), so I decided to use another API https://catfact.ninja/, so in order to make
my API "pluggable", easily change between cat fact API and the ninja API, I used flavors and the Clean Architecture structure also helped me a lot to achieve this result.
So we can easily change which API to use just changing the build variant (Menu Build-> Select Build Variant), so if you are testing this application try to use the catFactNinjaDebug variant first,
because it'll work, as for the catFactDebug will work after the developer fixes the bug on his endpoint.

So this is the structure of the app:

ui - Interface, MVP implementation
domain - Use Cases, middleware between UI (presenter) and the repository
repository - Rest calls, retrofit

and all tied together with dagger2 as dependency injection library

Tech stack: Clean Architecture, MVP, Kotlin, Mockito, Retrofit, RxJava, and Dagger2 
