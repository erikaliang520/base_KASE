# base_KASE Exploring APIS

## Description of Problem Domain

In today's interconnected world where open access to the internet and travel have brought people from diverse 
cultures closer together, proper communication is more imperative than ever.
Living in Canada, a melting pot of cultures, the ability to communicate in different languages is not just desirable,
but essential to forster understanding and inclusivity.
Recognizing the significance of proper communication in this global landscape, 
our team looks to contribute to the intersecting domain of accessibility and communication. 

This project seeks to provide language translation access for users and our primary goal is to provide users 
with seamless language translation capabilities.
Our application allows users to input text in one language and translates this text input into the desired language. 

Moreover, this application will go a step further by incorporating a text-to-speech feature for translated words.
This will enhance accessibility by providing and audio version of the translated text which is especially beneficial 
for individuals with visual impairments or who prefer auditory learning.
Not only will this take steps to solve the language barrier between individuals 
but also provide audio assistance for individuals to learn and understand new languages.

This approach of our project not only seeks to break down language barriers but encourages language
exploration and learning. By combining translation services with audio assistance, we aim to create a tool that
can help individuals forster a deeper appreciation for linguistic diversity. Through our commitment to 
accessibility and communication, we strive to contribute to a more inclusive and connected world.  

## Description of Application 

Our team is developing an application designed to bridge the communication gap between 
individuals who use sign language and those who do not. This application will serve 
as a interactive and engaging platform for learning and understanding sign language. 


The core functionalities of the application:

- Translating user text inputs into desired language text outputs. 
- Using text-to-speech features to create audio clips of user text inputs
- Saving a history of past words, translations, languages, and audios
- Generating suggested words of interest that may be laterally related to the user inputs

The user interface will be designed to ensure accessibility and ease-of-use for all users. 
The application uses Google Cloud APIs such as their translation and text-to-speech APIs. 
The translation API will be used to provide swift on-the-spot translations of user text inputs in a variety 
of desired languages. The text-to-speech API will provide the text-to-speech audio of the translated 
output words using different language and accent bases.


## Link to API Documentation

YouTube Data - https://developers.google.com/youtube/v3/docs
Open AI - https://platform.openai.com/docs/plugins/getting-started


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


hello 

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














