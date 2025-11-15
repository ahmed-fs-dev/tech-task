import { Link, Route, Routes, useNavigate } from 'react-router-dom';
import './App.css';
import TasksListSubPage from './sub-pages/tasks-list-subpage';
import TaskDetailsSubPage from './sub-pages/task-details-subpage';
import CreateTaskSubPage from './sub-pages/create-task-subpage';
import { BsPlus } from 'react-icons/bs';

function App(): JSX.Element {
    const navigate = useNavigate();
    
    return (
        <div className="App">
            <div className="header">
                <Link to="/" className="header-text">Tasks Tracker</Link>
                <Link to="/create" className="add-task-button"><BsPlus className="plus-icon" /> New Task</Link>
            </div>
            <div className="body">
                <Routes>
                    <Route path="/" element={<TasksListSubPage />} />
                    <Route path="/:taskId" element={<TaskDetailsSubPage />} />
                    <Route path="/create" element={<CreateTaskSubPage />} />
                </Routes>
            </div>           
        </div>
    );
}

export default App;
