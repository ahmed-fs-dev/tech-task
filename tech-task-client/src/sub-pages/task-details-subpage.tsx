import { useEffect, useRef, useState } from "react";
import "./task-details-subpage.scss";
import Checkbox from "../components/miscs/checkbox";
import { Controller, useForm } from "react-hook-form";
import { FaRegSave } from "react-icons/fa";
import { useNavigate, useParams } from "react-router-dom";
import TaskService from "../services/task-service";
import { Task } from "../types/task";
import { toast } from "react-toastify";

export default function TaskDetailsSubPage(): JSX.Element {
    const {
        register,
        handleSubmit,
        control,
        formState: { errors },
        reset
    } = useForm<Omit<Task, "id">>();
    const [dataIsLoaded, setDataIsLoaded] = useState(false);
    const { taskId } = useParams<{ taskId: string }>();
    let savedTaskData = useRef<Task | null>(null);
    const navigate = useNavigate();

    useEffect(() => {
        TaskService.getTaskById(Number(taskId)).then((task) => {
            reset(task);
            savedTaskData.current = task;
        });
        setDataIsLoaded(true);
    }, []);

    const onSubmit = (data: Omit<Task, "id">) => {
        TaskService.updateTask(Number(taskId), data).then(() => {
            toast.success('Task updated successfully', {
                position: "bottom-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            });
        }).catch((err) => {
        });
    };

    const onReset = () => {
        if (savedTaskData.current)
            reset(savedTaskData.current);
    };

    function deleteTask(e: React.MouseEvent) {
        e.stopPropagation();
        const isConfirmed = window.confirm('Are you sure you want to delete this task?');
        if (isConfirmed) {
            TaskService.deleteTask(Number(taskId)).then(() => {
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
                navigate("/");
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


    return <div className="tasks-details-subpage">
        {dataIsLoaded && (<form className="form" onSubmit={handleSubmit(onSubmit)}>
            <div className="task-details-container">
                <div className="field">
                    <label>Title</label>
                    <input className="field-value" type="text" {...register("title", {
                        required: "Title is required",
                        minLength: {
                            value: 3,
                            message: "Title must be at least 3 characters",
                        },
                        maxLength: {
                            value: 50,
                            message: "Title must be at most 50 characters",
                        },
                    })} />
                    {errors.title && <p style={{ color: "red" }}>{errors.title.message}</p>}
                </div>
                <div className="field">
                    <label>Description</label>
                    <textarea className="field-value" {...register("description")}></textarea>
                </div>
                <div className="field">
                    <label>Completed</label>
                    <Controller
                        name="completed"
                        control={control}
                        render={({ field }) => (<Checkbox checked={field.value} onChange={field.onChange} />)}
                    />
                </div>
            </div>
            <div className="actions">
                <div className="left-actions">
                    <button type="button" className="delete-button" onClick={deleteTask}>Delete</button>
                </div>
                <div className="right-actions">
                    <button type="button" className="reset-button" onClick={onReset}>Reset</button>
                    <button type="submit" className="submit-button"><FaRegSave className="save-icon" />Save</button>
                </div>
            </div>
        </form>)}
    </div>;
}