###### _

<img src="https://img.shields.io/badge/- File upload with Multipart File %20- blue" height=50px>

|     | Subject                                                                                |
| :-: | :------------------------------------------------------------------------------------- |
|     | [Introduction for setup with Vite](#Introduction)                          |
|  1  | [Vite setup](#1_vite_setup)                          |
|  2  | [index.html](#2_index_html)                          |
|  3  | [index.css](#3_index_css)                          |
|  4  | [main.jsx](#4_main_jsx)                          |



--- 

###### Introduction

<img src="https://img.shields.io/badge/- Introduction  %20-blue" height=40px>

This tutorial shows How to create a React app from Scartch, with Vite :

### [https://vitejs.dev/](https://vitejs.dev/)

See the main link from [vitejs.dev](https://vitejs.dev/) for:
1. benefits.
2. click on the `why` to see the explanation
3. See John Smilga react course for explanation [Vite with john smilga](https://www.udemy.com/course/react-tutorial-and-projects-course/learn/lecture/36135356#search)

https://blog.logrocket.com/vite-3-vs-create-react-app-comparison-migration-guide/


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 1_vite_setup

<img src="https://img.shields.io/badge/- 1. vite_setup  %20-blue" height=40px>

For Vite Setup:
1. need to use `.jsx` extensions
2. `index.html` is in the main project folder (instead of public folder when using create-react-app)
3. `assets` folder is under `src` folder 
4. instead of `index.js` need to use `main.jsx`
5. to run project use `npm run dev` instead of `npm start`
6. the rest is the same

Create React app with vite, following link [https://vitejs.dev/guide/](https://vitejs.dev/guide/)

### NOTE:

- My npm version is 6.14 thus I use this command
- name of project : `my-react-app` 
- I can select to create it also with TypeScript by typing `react-ts` instead of `react`.

The commands below dot he following:
- create a react app with Vite (in 3sec)
- install all dependencies in 20 secs (Instead 4min with create-react-app)
- run `npm run dev` which will open browse on port 5173 .  this is similar to `npm start` 

```
npm create vite@latest my-react-app --template react
npm install
npm run dev
```

We can see it cretated it in 2.07 seconds. </br>
Open the project folder with vscode and let's explore the folder structure

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/236320717-5197bb59-f55a-40a8-b4ec-ed3f07f4c9ac.png" width=750 height=240 />
</p>

### [folder structure with vite](#-)

This is how the structure of the folder arranged:

<p align=center>
  <img src="https://user-images.githubusercontent.com/36256986/236327351-b0ea0b4d-3769-4b1f-9f63-797caf9238bc.png"  />
</p>




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

--- 

###### 2_index_html

<img src="https://img.shields.io/badge/- 2_index_html  %20-blue" height=40px>

In `index.html` do the following:
1. modify the name inside the `<title>` tag , to a project name

### [icons to use](#-)

We can use the following icons:
1. Add `React icons` by typing `npm install react-icons --save` in the project folder (see lin of [react-icons](https://react-icons.github.io/react-icons/) )
2. Add the `cdn of font awesome` before the title tag  as below

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/svg+xml" href="/vite.svg" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://kit.fontawesome.com/c3d49f7cef.js"></script>
    <title>React App</title>
  </head>
  <body>
    <div id="root"></div>
    <script type="module" src="/src/main.jsx"></script>
  </body>
</html>
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 3_index_css

<img src="https://img.shields.io/badge/- 3_index_css  %20-blue" height=40px>

### [1. Normalize](#-)

Small CSS file that provides cross-browser consistency in the default styling of HTML elements.

Alternative/Fancier way of doing this

```css
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
```

- Go to [https://necolas.github.io/normalize.css/](https://necolas.github.io/normalize.css/)
- run command `npm install normalize.css` in the main app folder
- In main.jsx (Since this is Vite setup , we have main.jsx instad) we `import` the `normalize.css` before the `index.css`

```js
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import 'normalize.css';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
```

### [2. Global CSS](#-)

In `index.css` we set are Global CSS (Or we can set our entire CSS in it)

Copy the the css from file I created in GIT repo og HTML & CSS - [default CSS code](https://github.com/sshalem/HTML_CSS/blob/master/_4_default-CSS-starter/main.css)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 4_main_jsx

<img src="https://img.shields.io/badge/- 4_index_js  %20-blue" height=40px>

Vite don't have `index.js` 

This is how the `main.jsx` while doing setup (see the Gotch regarding the `React.StrictMode` in section 4.1):

```js
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

###### 4_1_react_strict_mode_gotch

<img src="https://img.shields.io/badge/- index js ,React StrictMode gotcha%20- green" height=32px>

In the `render` we have the root component `App`. </br>
We also have the `<React.StrictMode>`

[`React.StrictMode`](#-) 

* is a tool for highlighting potential problems in application. 
* It activates additional checks and warnings for its descendants.
* It only runs in development, so it's not going to impact your production build.

[StrictMode - Gotch](#-)

* Gotch is that in development it renders twice. 
* Now, if you want, you can remove Please keep that in mind. 
* It is an option if for some reason you don't like the warnings, you don't like the fact that it renders twice.
* It is not going to affect the production.

In the code below I remove the `StrictMode` , now we have an empty element.

```js
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import './index.css';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <>
    <App />
  <>
);
```




[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 5_App_jsx

<img src="https://img.shields.io/badge/- 5_App_jsx  %20-blue" height=40px>

Since App is our root component , and it's already build in when we created react app, lets setup the `App.jsx` as follows, and remove the boilerplate code. </br>

This is how I setup my `App.js` component ( I keep the App.css file in case I want to use customize CSS and overide the index.css Styling):

```js
import './App.css';

function App() {
  // set this to see that console showing this component
  console.log('hello');
  return <h1>temp app</h1>;
}

export default App;
```


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 6_components_folder

<img src="https://img.shields.io/badge/- 6_components_folder  %20-blue" height=40px>

* Create a `components` folder under `src` folder.
* Use the shortcut of `rafce` to create a `React Arrow Function Export Component`

![image](https://user-images.githubusercontent.com/36256986/230983981-6e458708-754d-457b-bf49-de3dc8dac8be.png)

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 7_images_assets_folder

<img src="https://img.shields.io/badge/- 7_images_assets_folder  %20-blue" height=40px>

With Vite, the assets folder is created automatically </br>
In this folder , all the images video's are stored.

![image](https://user-images.githubusercontent.com/36256986/230984806-a82b90b2-04e1-4be0-8f26-f74975d791ca.png)


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 8_install_react_router

<img src="https://img.shields.io/badge/- 8. Install React Router latest version 6 and up %20-blue" height=40px>

Type following command in the project folder , to install the `react-router` then check the `package.json` which version is installed.

```js
npm install react-router-dom@6
```

---


###### 9_build_for_production

<img src="https://img.shields.io/badge/- 9. build_for_production %20-blue" height=40px>

To build the project for production :

```
npm run build 
```

This will create a folder of `dist`. </br>

![image](https://user-images.githubusercontent.com/36256986/236332657-1248b250-a284-4fa8-8abb-893d22e8da66.png)

To Deploy:
1. with spring boot app , Need to take this whole content of the folder and add it to the Spring boot app static folder.
2. With google , Need to take this whole content of the folder and add it to the Spring boot app static folder.
3. With Netlify, no need to build , since Netlify takes the code from GitHub and builds it automatically. 


[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


###### 

<img src="https://img.shields.io/badge/- X  %20-yellow" height=40px>

<img src="https://img.shields.io/badge/- X %20- green" height=32px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/-X.   %20-yellow" height=35px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---


