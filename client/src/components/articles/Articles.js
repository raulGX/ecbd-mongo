import React from 'react';
import { Link } from 'react-router-dom'
import { API_URL } from '../common/constants'

class Articles extends React.Component {
  constructor(props) {
    super(props)
    this.state = { loaded: false, articles: [], page: 1, search: '' }
  }
  handleScroll = () => {
    const windowHeight = "innerHeight" in window ? window.innerHeight : document.documentElement.offsetHeight;
    const body = document.body;
    const html = document.documentElement;
    const docHeight = Math.max(body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight);
    const windowBottom = windowHeight + window.pageYOffset;
    if (windowBottom >= docHeight && this.state.loaded) {
      this.fetchData()
    }
  }
  componentDidMount() {
    this.fetchData()
    window.addEventListener("scroll", this.handleScroll);
  }

  componentWillUnmount() {
    window.removeEventListener("scroll", this.handleScroll);
  }

  search = () => {
    this.setState({ page: 1, loading: true, articles: [] }, () => this.fetchData())

  }

  fetchData = () => {
    fetch(API_URL + 'articles?page=' + this.state.page + '&search=' + this.state.search)
      .then(res => res.json())
      .then(data => this.setState({ articles: [...this.state.articles, ...data], loaded: true }))
      .catch(err => alert('error connecting to the server'))
    this.setState({ page: this.state.page + 1 })
  }
  render() {
    let { loaded, articles } = this.state
    return (
      <div className='container'>
        <div className="form-group">
          <label>Search</label>
          <input onChange={e => this.setState({ search: e.target.value })} value={this.state.search} type="text" className="form-control" placeholder="Title" required />
          <button onClick={this.search}>Search</button>
        </div>
        <h1>Articles page</h1>
        {articles.map((article, index) => (
          <div key={article.id}>
            <Link to={`/articles/${article.id}`}><h2>{article.name}</h2></Link>
            <p>{article.body.substr(0, 200)}</p>
            {index !== articles.length - 1 && <hr style={{ marginBottom: "30px" }} />}
          </div>
        ))}
        {loaded
          ? (<div>
          </div>)
          : (<p>Loading</p>)
        }
      </div>
    );
  }
}

export default Articles;
