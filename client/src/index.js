import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import { Route } from 'react-router';

import Header from './components/common/Header';
import Login from './components/common/Login';
import Register from './components/common/Register';
import HomePage from './components/home/HomePage';
import ArticlesPage from './components/articles/Articles';
import MyArticles from './components/articles/MyArticles';
import MyArticle from './components/articles/MyArticle';
import NewArticle from './components/articles/NewArticle';
import SpecificArticlePage from './components/articles/SpecificArticlePage';

import 'bootstrap/dist/css/bootstrap.css';
import './App.css'

render(
  <BrowserRouter>
    <div>
      <Header />
      <Route exact path='/' component={HomePage} />
      <Route exact path='/articles' component={ArticlesPage} />
      <Route exact path='/myarticles' component={MyArticles} />
      <Route path='/myarticles/:articleId' component={MyArticle} />
      <Route path='/newArticle' component={NewArticle} />
      <Route path='/articles/:articleId' component={SpecificArticlePage} />
      <Route path='/login' component={Login} />
      <Route path='/register' component={Register} />
    </div>
  </BrowserRouter>,
  document.getElementById('root')
);
