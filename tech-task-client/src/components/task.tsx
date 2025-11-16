import { useState } from 'react';
import Checkbox from './miscs/checkbox';
import './task.scss';
import { BsChevronRight } from 'react-icons/bs';
import { useNavigate } from 'react-router-dom';
import { MdOutlineDeleteForever } from 'react-icons/md';
import { Task as TaskType } from '../types/Task';
import TaskService from '../services/taskService';
import { toast } from 'react-toastify';

export default function Task(props: { task: TaskType, onTaskDeleted: () => void }): JSX.Element {
    const [checked, setChecked] = useState(props.task.completed);
    const navigate = useNavigate();

    function deleteTask(e: React.MouseEvent) {
        e.stopPropagation();
        const isConfirmed = window.confirm('Are you sure you want to delete this task?');
        if (isConfirmed) {
            TaskService.deleteTask(props.task.id).then(() => {
                toast.success('Task deleted successfully', {
                    position: "bottom-center",
                    autoClose: 5000,
                    hideProgressBar: true,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "light",
                });
                props.onTaskDeleted();
            }).catch((err) => {
                toast.error(`Error deleting task: ${err.message}`, {
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
        }
    }

    const toggleTaskCompleted = (newChecked: boolean) => {
        setChecked(newChecked);
        TaskService.updateTask(props.task.id, { ...props.task, completed: newChecked }).catch((err) => {
            toast.error(`Error updating task: ${err.message}`, {
                position: "bottom-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            });
            setChecked(!newChecked);
        });
    };

    return <div onClick={() => navigate(`/${props.task.id}`)} className="task">
        <div className="task-left">
            <Checkbox containerClassName="checkbox" checked={checked} onChange={toggleTaskCompleted} />
            <div className="content">
                <span className="link">{props.task.title}</span>
                <div className="description">{props.task.description}</div>
            </div>
        </div>
        <div className="task-actions">
            <BsChevronRight />
            <button onClick={deleteTask} className="delete-button"><MdOutlineDeleteForever className="delete-icon" /></button>
        </div>
    </div>;
}