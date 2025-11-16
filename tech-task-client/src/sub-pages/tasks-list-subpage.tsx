import { useEffect, useState } from "react";
import Task from "../components/task";

import './tasks-list-subpage.scss';
import TaskService from "../services/task-service";
import { Task as TaskType } from "../types/task";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";
import { BsPlus } from "react-icons/bs";
import { PiEmpty, PiEmptyBold, PiEmptyFill } from "react-icons/pi";



export default function TasksListSubPage(): JSX.Element {
    const [tasks, setTasks] = useState<TaskType[]>();

    useEffect(() => {
        refresh();
    }, []);

    const refresh = () => {
        TaskService.getTasks().then((data) => {
            setTasks(data.tasks);
        }).catch((err) => {
            toast.error(`Error fetching tasks: ${err.message}`, {
                position: "bottom-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            });
        });
    };


    return <div className="tasks-list-subpage">
        <div className="tasks-container">
            <div className="body-header">
                <div>
                    <span className="body-header-title">My Tasks</span>
                    <div className="body-header-subtitle">Manage your tasks efficiently</div>
                </div>
                <Link to="/create" className="add-task-button"><BsPlus className="plus-icon" /> Add Task</Link>
            </div>
            {tasks && tasks.length > 0 && tasks.map((task) => (
                <Task key={task.id} task={task} onTaskDeleted={refresh} />
            ))}
            {tasks && tasks.length === 0 && <div className="no-tasks-found">
                <PiEmpty className="no-task-found-icon" />
                <span className="no-task-found-message">No tasks found. Click "Add Task" to create your first task.</span>
            </div>}
        </div>
    </div>;
}