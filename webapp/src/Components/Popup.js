import React, { useEffect, useState } from 'react';
import '../css/Popup.css';

const Popup = ({ message, displayTime }) => {
    const [isShown, setIsShown] = useState(true);

    useEffect(() => {
        const timer = setTimeout(() => {
            setIsShown(false);
        }, displayTime);

        return () => clearTimeout(timer);
    }, [displayTime]);

    return isShown ? <div className="popup">{message}</div> : null;
};

export default Popup;
