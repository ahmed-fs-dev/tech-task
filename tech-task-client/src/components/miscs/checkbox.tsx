// a custom checkbox component
import './checkbox.css';

export default function Checkbox(props: { checked: boolean; onChange: (checked: boolean) => void }): JSX.Element {
    function handleToggle(e: React.MouseEvent) {
        e.stopPropagation();
        props.onChange(!props.checked);
    }

    return <label className="custom-checkbox" onClick={handleToggle}>
        { props.checked && <span className="✓">✓</span>}
    </label>;
}