# WhereGo? RESTfull API web service

Spring Boot project for tourism promotion and hotel booking. This RESTful web service serves as the backend for a tourism and hospitality platform, allowing users to discover and book hotels, access tourist information, and plan their travel experiences.

## Tech Stack

- **Spring Framework:** Spring Boot, Spring Web, Spring Security, Spring Data JPA
- **Language:** Java

## Maven external dependencies
- Lombok
- Mapstruct
- Hibernate validator
- Jsonwebtoken

## API Reference
User roles:
- [Guest/Public](#role-guestpublic)
	- [Hotel api](#hotel-api-)
		- [Get all hotel](#get-all-hotel)
		- [Get random hotel](#get-hotel-random)
		- [Get hotel](#get-hotel)
		- [Get hotel filter information](#get-hotel-filter-information)
	- [Place api](#place-api-)
		- [Get all place](#get-all-place)
		- [Get random place](#get-place-random)
		- [Get place](#get-place)
		- [Get place filter information](#get-place-filter-information)
	- [Restaurant api](#restaurant-api-)
		- [Get all restaurant](#get-all-restaurant)
		- [Get random restaurant](#get-restaurant-random)
		- [Get restaurant](#get-restaurant)
		- [Get restaurant filter information](#get-restaurant-filter-information)
	- [Article api](#article-api-)
		- [Get all article](#get-all-article)
		- [Get article random](#get-article-random)
		- [Get article](#get-article)
	- [Search api](#search-api-)
		- [Search by name and category](#search-by-name-and-category)
	- [Share api](#share-api-)
		- [Render image](#render-image)
	- [User Authentication api](#user-authentication-api-)
		- [Login](#login)
		- [Writer Register](#writer-register)
		- [Check Writer Username Exist](#check-writer-username-exist)
		- [Check Writer Email Exist](#check-writer-email-exist)
		- [Traveler Register](#traveler-register)
		- [Check Traveler Username Exist](#check-traveler-username-exist)
		- [Check Traveler Email Exist](#check-traveler-email-exist)
- [Traveler](#role-traveler-)
	- [Get traveler detail](#get-traveler-detail)
	- [Update account](#update-account)
	- [Change password](#change-password)
	- [Review](#review-hotelrestaurantplace)
	- [Booking](#booking)
- [Writer](#role-writer-)
	- [Get writer detail](#get-writer-detail)
	- [Update account](#update-account-1)
	- [Change password](#change-password-1)
	- [Create article](#create-article)
	- [Update article](#update-article)
---
### Role Guest/Public

#### Hotel api [↥](#api-reference)

##### Get all hotel

```http
  GET /api/hotel
```
##### Get hotel random

```http
  GET /api/hotel/random?quantity={quantity}
```

| Parameter | Type | Description |
|-----------|------|-------------|
| `quantity` | `Integer` | **Not required.** Default value is 20 |

##### Get hotel

```http
  GET /api/hotel/{hotel_id}
```
| Parameter | Type  | Description|
|--|--|--|
| `hotel_id` | `Long` | **Required.** Id of hotel to fetch |

##### Get hotel filter information

```http
  GET /api/hotel/filter-infor
```

#### Place api [↥](#api-reference)

##### Get all place

```http
  GET /api/place
```
##### Get place random

```http
  GET /api/place/random?quantity={quantity}
```

| Parameter | Type | Description |
|-----------|------|-------------|
| `quantity` | `Integer` | **Not required.** Default value is 20 |

##### Get place

```http
  GET /api/place/{place_id}
```
| Parameter | Type  | Description|
|--|--|--|
| `place_id` | `Long` | **Required.** Id of place to fetch |

##### Get place filter information

```http
  GET /api/place/filter-infor
```

#### Restaurant api [↥](#api-reference)

##### Get all restaurant

```http
  GET /api/restaurant
```

##### Get restaurant random

```http
  GET /api/restaurant/random?quantity={quantity}
```

| Parameter | Type | Description |
|-----------|------|-------------|
| `quantity` | `Integer` | **Not required.** Default value is 20 |

##### Get restaurant

```http
  GET /api/restaurant/{restaurant_id}
```
| Parameter | Type  | Description|
|--|--|--|
| `restaurant_id` | `Long` | **Required.** Id of restaurant to fetch |

##### Get restaurant filter information

```http
  GET /api/restaurant/filter-infor
```
#### Article api [↥](#api-reference)

##### Get all article

```http
  GET /api/article
```
##### Get article random

```http
  GET /api/article/random?quantity={quantity}
```

| Parameter | Type | Description |
|-----------|------|-------------|
| `quantity` | `Integer` | **Not required.** Default value is 20 |

##### Get article

```http
  GET /api/article/detail/{article_id}
```
| Parameter | Type  | Description|
|--|--|--|
| `article_id` | `Long` | **Required.** Id of article to fetch |

#### Search api [↥](#api-reference)

##### Search by name and category

```http
  GET /api/search?category={category}&keyword={keyword}
```

| Parameter | Type | Description |
|-----------|------|-------------|
| `category` | `String` | **Required.** Value in range: `'hotel'|'restaurant'|'place'|'article'` |
|`keyword`|`String`|**Required.** The keyword searches for items with matching names|


#### Share api [↥](#api-reference)

##### Render image

```http
  GET /api/render/{image_file}
```

| Parameter | Type | Description |
|-----------|------|-------------|
| `image_file` | `String` | **Required.** The full file name to render |

#### User authentication api [↥](#api-reference)

##### Login

```http
  POST /api/auth/login?role={user_role}
```
| Parameter | Type | Description |
|-----------|------|-------------|
| `user_role` | `String` | **Required.** Specify login as role `writer` or role `traveler` |

Request body (**JSON format**)

| Parameter | Type  | Description|
|--|--|--|
| `email ` | `String` | **Required.** Email for login |
| `password` | `String` | **Required.** Account password |

##### Writer register

```http
  POST /api/auth/writer/register
```
Request body (**Form-data**)

| Parameter | Type  | Description|
|--|--|--|
| `email ` | `String` | **Required.** Email for login |
| `name` | `String` | **Required.** Full name |
| `tels` | `String` | **Required.** Phone number |
| `dob` | `Date:YYYY-MM-DD`| **Required.** Date of birth |
| `username` | `String` | **Required.** Account username |
| `password` | `String` | **Required.** Account password |

##### Check writer username exist

```http
  POST /api/auth/writer/check-username-exist
```
Request body (**x-www-form-urlencoded**)

| Parameter | Type  | Description|
|--|--|--|
| `username` | `String` | **Required.** Username to check existence |

##### Check writer email exist

```http
  POST /api/auth/writer/check-email-exist
```
Request body (**x-www-form-urlencoded**)

| Parameter | Type  | Description|
|--|--|--|
| `email` | `String` | **Required.** Email to check existence |

##### Traveler register

```http
  POST /api/auth/traveler/register
```
Request body (**Form-data**)

| Parameter | Type  | Description|
|--|--|--|
| `email ` | `String` | **Required.** Email for login |
| `name` | `String` | **Required.** Full name |
| `tels` | `String` | **Required.** Phone number |
| `dob` | `Date:YYYY-MM-DD`| **Required.** Date of birth |
| `username` | `String` | **Required.** Account username |
| `password` | `String` | **Required.** Account password |

##### Check traveler username exist

```http
  GET /api/auth/traveler/check-username-exist?username={username}
```

| Parameter | Type  | Description|
|--|--|--|
| `username` | `String` | **Required.** Username to check existence |

##### Check traveler email exist

```http
  GET /api/auth/traveler/check-email-exist?email={email}
```

| Parameter | Type  | Description|
|--|--|--|
| `email` | `String` | **Required.** Email to check existence |

### Role Traveler [↥](#api-reference)
 - Access requires the `traveler` role
 - Attach the **Bearer Token** to the **request header** for access

##### Get traveler detail

```http
  GET /api/traveler
```
##### Update account

```http
  POST /api/traveler/update
```
Request body (**Form-data**)

| Parameter | Type  | Description|
|--|--|--|
| `name` | `String` | **Not required.** Full name |
| `tels` | `String` | **Not required.** Phone number |
| `dob` | `Date:YYYY-MM-DD`| **Not required.** Date of birth |
| `username` | `String` | **Not required.** Account username |
| `avatar` | `File` | **Not required.** Account avatar |
##### Change password

```http
  POST /api/traveler/change-password
```
Request body (**JSON format**)

| Parameter | Type  | Description|
|--|--|--|
| `currentPassword` | `String` | **Required.** Current account password |
| `newPassword` | `String` | **Required.** New account password |

##### Review hotel/restaurant/place

```http
  POST /api/traveler/review?category={category}
```
| Parameter | Type  | Description|
|--|--|--|
| `category` | `String` | **Required.** Specify a review for `hotel`, `restaurant` or `place` |

Request body (**Form-data**)

| Parameter | Type  | Description|
|--|--|--|
| `commment` | `String` | **Required.** Leave a comment about the object |
| `rating` | `Integer` | **Required.** Provide a rating between 1 and 5 for the object |
| `categoryId` | `Long`| **Required.** The id of the object |
##### Booking
```http
  POST /api/traveler/booking
```

Request body (**JSON format**)

| Parameter | Type  | Description|
|--|--|--|
| `hotelId` | `Long` | **Required.** The id of the hotel booking |
| `people` | `Integer` | **Required.** Total people |
| `price` | `Long`| **Required.** Total booking price |
| `checkIn` | `Date:YYYY-MM-DD`| **Required.** Check-in date |
| `checkOut` | `Date:YYYY-MM-DD`| **Required.** Check-out date |

### Role Writer [↥](#api-reference)
 - Access requires the `writer` role
 - Attach the **Bearer Token** to the **request header** for access

##### Get writer detail

```http
  GET /api/writer
```
##### Update account

```http
  POST /api/writer/update
```
Request body (**Form-data**)

| Parameter | Type  | Description|
|--|--|--|
| `name` | `String` | **Not required.** Full name |
| `tels` | `String` | **Not required.** Phone number |
| `dob` | `Date:YYYY-MM-DD`| **Not required.** Date of birth |
| `username` | `String` | **Not required.** Account username |
| `avatar` | `File` | **Not required.** Account avatar |

##### Change password

```http
  POST /api/writer/change-password
```
Request body (**JSON format**)

| Parameter | Type  | Description|
|--|--|--|
| `currentPassword` | `String` | **Required.** Current account password |
| `newPassword` | `String` | **Required.** New account password |

##### Create article

```http
  POST /api/article/create
```
Request body (**Form-data**)

| Parameter | Type  | Description|
|--|--|--|
| `title` | `String` | **Required.** Article title|
| `shortDescription` | `String` | **Required.** Article short content |
| `content` | `String` | **Required.** Article content |
| `thumbnail` | `File` | **Required.** Article thumbnail |

##### Update article

```http
  POST /api/article/update/{article_id}
```
| Parameter | Type  | Description|
|--|--|--|
| `article_id` | `Long` | **Required.** The id of the article to update |

Request body (**Form-data**)

| Parameter | Type  | Description|
|--|--|--|
| `title` | `String` | **Not Required.** Article title|
| `shortDescription` | `String` | **Not Required.** Article short content |
| `content` | `String` | **Not Required.** Article content |
| `thumbnail` | `File` | **Not Required.** Article thumbnail |
