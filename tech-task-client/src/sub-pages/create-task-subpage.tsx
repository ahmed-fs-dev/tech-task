import { useState } from "react";
import Checkbox from "../components/miscs/checkbox";
import { Controller, useForm } from "react-hook-form";
import { FaRegSave } from "react-icons/fa";

import "./create-task-subpage.scss";
import { Task } from "../types/task";
import TaskService from "../services/task-service";
import { useNavigate } from "react-router-dom";
import { Bounce, toast } from "react-toastify";
import { getErrorMessageForUser } from "../utils/error-utils";

export default function CreateTaskSubPage(): JSX.Element {
    const {
        register,
        handleSubmit,
        control,
        formState: { errors },
        reset
    } = useForm<Omit<Task, "id">>();
    const [savePending, setSavePending] = useState(false);
    const navigate = useNavigate();

    const onSubmit = (data: Omit<Task, "id">) => {
        // Disables the save button to prevent multiple submissions
        setSavePending(true);
        TaskService.createTask(data).then(() => {
            navigate("/");
            toast.success('Task created successfully', {
                position: "bottom-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
                transition: Bounce,
            });
        }).catch((err) => {
            setSavePending(false);
            toast.error(`Error creating task: ${getErrorMessageForUser(err)}`, {
                position: "bottom-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
                transition: Bounce,
            });
        });
    };

    const onReset = () => {
        reset();
    };

    return (<div className="create-task-subpage">
        <div className="content">
            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="task-details-container">
                    <div className="field">
                        <label>Title</label>
                        <input className="field-value" placeholder="Enter task title" type="text" {...register("title", {
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
                        <textarea className="field-value" placeholder="Enter task description" {...register("description", {
                            maxLength: {
                                value: 255,
                                message: "Description must be at most 255 characters"
                            }
                        })}></textarea>
                    {errors.description && <p style={{ color: "red" }}>{errors.description.message}</p>}
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
                <div className="task-details-form-actions">
                    <button type="button" onClick={onReset} className="reset-button">Clear</button>
                    <button type="submit" disabled={savePending} className="task-details-form-submit-button"><FaRegSave className="save-icon" />Save</button>
                </div>
            </form>
        </div>
    </div>);
}