import { useState } from 'react';
import './App.css';

interface FoodItem {
  id: number,
  name: string
}

function App() {
  const [foodItems, setFoodItems] = useState<FoodItem[]>([{id: 1, name: 'Apple'}, {id: 2, name: 'Banana'}]);
  const [newFoodItem, setNewFoodItem] = useState<string>('');

  const foodItemCreation = (event: React.SyntheticEvent) => {
    event.preventDefault()
    const foodItemToAdd = {
      id: foodItems.length + 1,
      name: newFoodItem,
    }
    console.log(foodItemToAdd);
    setFoodItems(foodItems.concat(foodItemToAdd));
    setNewFoodItem('')
  };

  return (
    <div className="App">
      <div>
        <form onSubmit={foodItemCreation}>
          <input type="text" value={newFoodItem} onChange={e => setNewFoodItem(e.target.value)} />
          <button type="submit">add</button>
        </form>
        <ul>
          {foodItems.map(item =>
            <li key={item.id}>{item.name}</li>
          )}
        </ul>
      </div>
    </div>
  );
}

export default App;
