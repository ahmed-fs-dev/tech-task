import { useEffect, useState } from "react";
import "./task-details-subpage.css";
import Checkbox from "../components/miscs/checkbox";
import { Controller, useForm } from "react-hook-form";
import { FaRegSave } from "react-icons/fa";

export default function TaskDetailsSubPage(): JSX.Element {
    const {
        register,
        handleSubmit,
        control,
        formState: { errors },
        reset
    } = useForm<{
        title: string;
        description: string;
        completed: boolean;
    }>();
    const [dataIsLoaded, setDataIsLoaded] = useState(false);

    useEffect(() => {
        reset({
            title: 'Sample Task',
            description: 'This is a sample task description.',
            completed: false
        });
        setDataIsLoaded(true);
    }, []);

    const onSubmit = (data: { title: string; description: string; completed: boolean }) => {
        
    };

    return <div className="tasks-details-subpage">
        <div className="content">
            {dataIsLoaded && (<form onSubmit={handleSubmit(onSubmit)}>
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
                <div className="task-details-form-actions">
                    <button type="button" className="reset-button">Reset</button>
                    <button type="submit" className="task-details-form-submit-button"><FaRegSave className="save-icon"/>Save</button>
                </div>
            </form>)}
        </div>
    </div>;
}