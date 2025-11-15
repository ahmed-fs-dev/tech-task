import { useState } from 'react';
import Checkbox from './miscs/checkbox';
import './task.css';
import { BsChevronRight } from 'react-icons/bs';
import { useNavigate } from 'react-router-dom';
import { MdOutlineDeleteForever } from 'react-icons/md';

export default function Task(props: { task: { id: number; title: string; description: string; completed: boolean } }): JSX.Element {
    const [checked, setChecked] = useState(props.task.completed);
    const navigate = useNavigate();

    function deleteTask(e) {
        e.stopPropagation();
        const isConfirmed = window.confirm('Are you sure you want to delete this item?');
        if (isConfirmed) {
            // Perform the deletion logic here
            console.log('Item deleted!');
        } else {
            console.log('Deletion cancelled.');
        }
    }

    return <div onClick={() => navigate(`/${props.task.id}`)} className="task">
        <div className="task-left">
            <Checkbox checked={checked} onChange={setChecked} />
            <div className="task-content">
                <span className="task-link">{props.task.title}</span>
                <div className="task-description">{props.task.description}</div>
            </div>
        </div>
        <div className="task-actions">
            <BsChevronRight />
            <button onClick={deleteTask} className="delete-action-button"><MdOutlineDeleteForever className="delete-action" /></button>
        </div>
    </div>;
}