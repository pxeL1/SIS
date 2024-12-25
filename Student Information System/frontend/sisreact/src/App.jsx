import './App.css'
import {HashRouter as Router, Routes, Route} from "react-router-dom";
import {Home} from './pages/Home.jsx';
import {Profile} from './pages/Profile';
import {Course} from './pages/Course.jsx';
import {Messages} from './pages/Messages';
import {Layout} from "./Layout.jsx";

function App() {

  return (
      <Router>
          <Routes>
              <Route element={<Layout/>}>
                  <Route path="/" element={<Home/>} />
                  <Route path="/profile" element={<Profile/>} />
                  <Route path="/course" element={<Course/>} />
                  <Route path="/messages" element={<Messages/>} />
              </Route>
          </Routes>
      </Router>
  )
}

export default App
