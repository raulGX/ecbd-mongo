import React from 'react';
import { Button, Modal, FormControl, ControlLabel } from 'react-bootstrap';

import { API_URL } from '../common/constants'
import axios from 'axios';


class MyArticle extends React.Component {
  constructor(props) {
    super(props)
    this.state = { article: null }
  }

  componentDidMount() {
    axios.get(API_URL + 'articles/' + this.props.match.params.articleId)
      .then(res => this.setState({ article: res.data }))
  }

  onChange = name => e => this.setState({ article: { ...this.state.article, [name]: e.target.value } })

  save = e => {
    e.preventDefault()
    axios.put(API_URL + 'articles/' + this.props.match.params.articleId, this.state.article)
      .then(() => {
        alert('article saved!')
        this.props.history.push('/myArticles')
      })
      .catch((err) => alert(err.message))
  }

  render() {
    let { article } = this.state
    if (!article) {
      return <p>Loading</p>
    }

    return (
      <div className="container">
        <div className="row">
          <form onSubmit={this.save} className="col-md-12">
            <div className="input-group">
              <label>Title</label>
              <input onChange={this.onChange('name')} value={this.state.article.name} type="text" className="form-control" placeholder="Title" required />
            </div>

            <div className="input-group">
              <label>Body</label>
              <textarea onChange={this.onChange('body')} value={this.state.article.body} type="text" className="form-control" placeholder="Body" required />
            </div>
            <button type="submit">Save</button>
          </form>
        </div>
      </div>


    );
  }
}

export default MyArticle;
