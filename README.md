# base_KASE Exploring APIS

## Description of Problem Domain

Our team focuses on the intersecting domain of accessibility, communication, and education. 
Today, there is still a significant communication barrier between individuals who use sign language 
as those who do not understand sign language. 
There are a significant amount of individuals who do not understand sign language but would like
to learn. However, learning sign language proves to be a challenging task due to the lack of 
interactive and user-friendly resources.  

This project seeks to allow and motivate more individuals to learn sign language by developing 
an application that translate text input into corresponding sign language gestures. Moreover, 
this application will also take in speech input, translate it to text, and then display the 
corresponding sign language gesture. Not only will this take steps to solve the communication 
barrier but also provide an engaging method for individuals to learn and understand sign language.

## Description of Application 

Our team is developing an application designed to bridge the communication gap between 
individuals who use sign language and those who do not. This application will serve 
as a interactive and engaging platform for learning and understanding sign language. 

The core functionalities of the application:

- Translating text input into corresponding sign language gestures displayed through YouTube videos.
- Converting speech input into text using Google's Speech-to-Text API, which is then 
translated into corresponding sign language gestures represented via videos sourced from YouTube.

The user interface will be designed to ensure accessibility and ease-of-use for all users. 
The application uses Google's Speech-to-Text API for accurate voice recognition, along with 
the YouTube API to provide a vast library of sign language gesture videos.

In essence, our application acts as an interpreter and translator, translating spoken 
or written words into sign language through relevant video content. 
Additionally, it serves as an educational tool that enables users to learn sign language 
at their own pace 

We believe this solution has potential to increase communication 
and promote inclusivity for deaf and hard-of-hearing communities while offering an 
easy-to-use method for individuals interested in learning sign language.

## Link to API Documentation

Google's Speech-to-Text - https://cloud.google.com/speech-to-text/docs#docs
YouTube Data - https://developers.google.com/youtube/v3/docs

## Using Postman to Try Out YouTube API

Expected Results When Calling Code in Java 
<img width="1434" alt="Postman Youtube API" src="https://github.com/erikaliang520/base_KASE/assets/113625685/acfc9050-e0b6-48dd-9573-81e028fd53e5">

Testing YouTube API on Google
<img width="1509" alt="Expected Results when Calling in Java" src="https://github.com/erikaliang520/base_KASE/assets/113625685/5c5d3065-d34f-4804-81f2-7d0e9a5ac4d4">

We sent a GET request to the Youtube Api server on postman, since we're requesting data on a search relevant to our filters.

We used GET for the use case list (by keyword). It needs access to our API key, and we used parameters and filters for:
part = snippet
max Results = 3
q = "ASL letter a"

The results return the max Result amount of youtube videos found using the search list API. It also includes attributes like potential video height/width sizes, video title, channel name, duration, etc.


## Java Code Process and Technical Problems

Dependencies Run Successfully
<img width="1512" alt="Dependencies Success" src="https://github.com/erikaliang520/base_KASE/assets/113625685/165ecc2d-22b6-4584-ab6f-c90f815715ec">

Two Imports Still Fail after Clicking Invalidate and Restart Caches
<img width="1512" alt="2 imports failed - all other imports are working after we clicked invalidate and restart caches" src="https://github.com/erikaliang520/base_KASE/assets/113625685/78548c50-46dd-4620-9f85-f569a549314b">

To Solve Import Problem, We Tried to Reload and Refresh But was Unsuccessful
<img width="1017" alt="Reloading and refreshing wanting to solve import problem but it didn't work " src="https://github.com/erikaliang520/base_KASE/assets/113625685/d11a3391-3c82-4b46-9fc5-ea92907d1ec2">

Since Reloading and Refreshing was Unsuccessful, We Tried to Restart Build in Terminal but Also Unsuccessful
<img width="1512" alt="Refresh didn't work so tried to restart build in terminal - has something to do with not working" src="https://github.com/erikaliang520/base_KASE/assets/113625685/52b68dd5-038b-4ba3-a012-adfca68ab9d4">

Java Code - When Running Java File, This is the Error that It Outputs 
<img width="1512" alt="when you try to run java file - java code this is the error that it outputs" src="https://github.com/erikaliang520/base_KASE/assets/113625685/43909fe2-55cb-4793-bc07-55d3d97a65bd">














