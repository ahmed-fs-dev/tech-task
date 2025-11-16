import { Link, Route, Routes } from 'react-router-dom';
import './App.scss';
import TasksListSubPage from './sub-pages/tasks-list-subpage';
import TaskDetailsSubPage from './sub-pages/task-details-subpage';
import CreateTaskSubPage from './sub-pages/create-task-subpage';
import { BsPlus } from 'react-icons/bs';
import { ToastContainer } from 'react-toastify';
import { ReactComponent as Logo } from './logo.svg';

function App(): JSX.Element {

    return (
        <div className="App">
            <div className="header">
                <Link to="/" className="header-text-logo">
                    <Logo className="app-logo" />
                    TasksTracker
                </Link>
                <span className="sub-header">Simple Task Management</span>
            </div>
            <div className="body">
                <Routes>
                    <Route path="/" element={<TasksListSubPage />} />
                    <Route path="/:taskId" element={<TaskDetailsSubPage />} />
                    <Route path="/create" element={<CreateTaskSubPage />} />
                </Routes>
            </div>
            <ToastContainer />
        </div>
    );
}

export default App;
