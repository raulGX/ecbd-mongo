import React from 'react';

import { API_URL } from '../common/constants'
import axios from 'axios';


class NewArticle extends React.Component {
  constructor(props) {
    super(props)
    this.state = { article: { name: '', body: '' } }
  }

  onChange = name => e => this.setState({ article: { ...this.state.article, [name]: e.target.value } })

  save = e => {
    e.preventDefault()
    let token = localStorage.getItem('token')
    axios.post(API_URL + 'articles', this.state.article, { headers: { Authorization: token } })
      .then(() => {
        alert('article saved!')
        this.props.history.push('/myArticles')
      })
      .catch((err) => alert(err.message))
  }

  render() {
    let { article } = this.state
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-8 col-md-offset-2">
            <form onSubmit={this.save}>
              <div className="form-group">
                <label>Title</label>
                <input onChange={this.onChange('name')} value={this.state.article.name} type="text" className="form-control" placeholder="Title" required />
              </div>

              <div className="form-group">
                <label>Body</label>
                <textarea onChange={this.onChange('body')} value={this.state.article.body} type="text" className="form-control" placeholder="Body" required />
              </div>
              <button type="submit">Save</button>
            </form>
          </div>
        </div>
      </div>


    );
  }
}

export default NewArticle;
