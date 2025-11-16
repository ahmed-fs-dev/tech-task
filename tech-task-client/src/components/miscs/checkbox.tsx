// a custom checkbox component
import './checkbox.scss';

export default function Checkbox(props: { checked: boolean; onChange: (checked: boolean) => void; containerClassName?: string }): JSX.Element {
    function handleToggle(e: React.MouseEvent) {
        e.stopPropagation();
        props.onChange(!props.checked);
    }

    return <div className={`custom-checkbox ${props.containerClassName}`} onClick={handleToggle}>
        {props.checked && <span className="checkmark">✓</span>}
    </div>;
}