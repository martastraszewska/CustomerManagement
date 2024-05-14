import { Button } from '@mui/material';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

interface CustomerDeleteDialogProps {
  open: boolean;
  onClose: () => void;
  deleteId: string;
  firstName: string;
  lastName: string;
  company: string;
  onDeleteCustomer: (id: string) => void;
}

export const CustomerDeleteDialog = ({
  open,
  onClose,
  deleteId,
  firstName,
  lastName, 
  company,
  onDeleteCustomer,
}: CustomerDeleteDialogProps) => {
  const handleCloseDeleteDialog = () => {
    onClose();
  };

  const handleDelete = async () => {
    onDeleteCustomer(deleteId);
    onClose();
  };

  return (
    <Dialog open={open} onClose={handleCloseDeleteDialog}>
      <DialogTitle>Usun klienta</DialogTitle>

      <DialogContent>
        <DialogContentText>
          Czy jestes pewien, ze chcesz usunac klienta <span style={{ color: 'red' }}>{firstName} {lastName} {company}</span> ?
        </DialogContentText>
      </DialogContent>

      <DialogActions>
        <Button onClick={handleCloseDeleteDialog}>Cofnij</Button>
        <Button onClick={handleDelete}>Zatwierdz</Button>
      </DialogActions>
    </Dialog>
  );
};
