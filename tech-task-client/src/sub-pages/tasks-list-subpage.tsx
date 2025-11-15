import { useEffect, useState } from "react";
import Task from "../components/task";

import './tasks-list-subpage.css';
import { Link } from "react-router-dom";



export default function TasksListSubPage(): JSX.Element {
    const [tasks, setTasks] = useState<{ id: number; title: string; description: string; completed: boolean }[]>();

    useEffect(() => {
        setTasks([
            { id: 1, title: 'Sample Task', description: 'This is a sample task description.', completed: false },
        ])
    }, []);

    return <div className="tasks-list-subpage">
        <div className="tasks-container">
            {tasks && tasks.map((task) => (
                <Task key={task.id} task={task} />
            ))}
        </div>
    </div>;
}