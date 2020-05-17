import { format, parse } from 'date-fns';

export class DateUtils {

    static formatDate(date: any): string {
        return format(date, 'yyyy-MM-dd');
    }

    static formatTime(time: any): string {
        return format(time, 'HH:mm:ss');
    }

    static formatDateTime(dateTime: any): string {
        return format(dateTime, 'yyyy-MM-dd HH:mm:ss');
    }

    static parse(dateTime: string): Date {
        return parse(dateTime, 'yyyy-MM-dd HH:mm:ss', new Date());
    }

}
